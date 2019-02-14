#import findWord
import string
import nltk
import pickle
from nltk.tokenize import PunktSentenceTokenizer
from nltk.tokenize import word_tokenize
from nltk.stem.wordnet import WordNetLemmatizer

convoFile = open("convo.dat", "r")
topic = 0
saveAnswer = False
lastAnswer = ""
lastType = ""
memory = {}

#get word stem
def lemma(word):
	l = nltk.WordNetLemmatizer()

	if l.lemmatize(word, pos='v') != word:
		answer = l.lemmatize(word, pos='v')
	else:
		answer = l.lemmatize(word)

	return answer

#get the relevant word from the user's answer
def findAnswer(uIn, wordType):
	with open('sent_tokenizer.pickle', 'rb') as f:
		tokenizer = pickle.load(f)


	if len(uIn.split(' ')) == 1:
		return lemma(uIn)

	tokenized = tokenizer.tokenize(uIn)
	tagged = list()
	matches = 0
	worseType = wordType[:-1]
	worseMatches = 0

	try:
		for t in tokenized:
			words = nltk.word_tokenize(t)
			tagged += list(nltk.pos_tag(words))

		numWords = len(tagged)
		for i in range(0, numWords):
			if tagged[i][1] == "VBP":
				pass
			elif tagged[i][1] == wordType:
				matches += 1
			elif worseType in tagged[i][1]:
				worseMatches += 1

		answer = "NaN"
		if matches == 1:
			for i in range(0, len(tagged)):
				if tagged[i][1] == wordType:
					answer = lemma(tagged[i][1])
					if "NN" not in tagged[i][1]:
						answer = answer.lower()
		elif worseMatches == 1:
			for i in range(0, len(tagged)):
				if tagged[i][1] == "VBP":
					pass
				elif worseType in tagged[i][1]:
						answer = lemma(tagged[i][0])
						if "NN" not in tagged[i][1]:
							answer = answer.lower()
		return answer
	except Exception as e:
		print(str(e))

#find any one word from a string array in a string
def findWord(words, string):
	for w in words:
		if (' ' + w + ' ') in (' ' + string + ' '):
			return True

#get a line by line number
#def getLine(lineNumber):
#	convoFile.seek(0)
#	for i, line in convoFile:
#		if i == lineNumber-1:
#			return line


#get a line by string search
#def findLine(str):
#	convoFile.seek(0)
#	for line in convoFile:
#		if str in line:
#			return line

#get a line by topic number
def getTopic():
	global topic
	count = -1
	convoFile.seek(0)
	while True:
		line = convoFile.readline()
		if not line: break
		if count == topic:
			line = convoFile.readline()
			return line
		elif line == "\n":
			count += 1

def printResponse(response):
	global saveAnswer
	global lastAnswer
	global lastType

	if '$' in response:
		r = response.split(' ')
		for e in r:
			if '$' in e:
				r2 = e
		index = r.index(r2)
		r2 = r2.translate(str.maketrans('', '', string.punctuation))
		if r2[-1:] == '\n':
			r2 = r2[:-1]
		response = response.replace('$' + r2, memory[r2])
	if '^' in response:
		r = response.split('^')
		r2 = r[1].split('.')
		saveAnswer = True
		lastAnswer = r2[0]
		lastType = r2[1][:-1]
		output = r[0]
		output = r[0]
	else:
		output = response

	if output[-1:] == '\n':
		output = output[:-1]
	print(output)

#finds most appropriate topic from user input
def findTopic(uIn):
	global topic
	global saveAnswer
	maxMatches = 0
	topMatch = -1
	matches = 0
	count = 0
	firstLine = True

	convoFile.seek(0)
	convoFile.readline()
	while True:
		line = convoFile.readline()
		if not line: break
		if firstLine:
			andSplit = line[1:-1].split('&')
			for a in andSplit:
				orSplit = a.split('/')
				if findWord(orSplit, uIn):
					matches += 1
			firstLine = False
		elif line == "\n":
			if matches > maxMatches:
				maxMatches = matches
				topMatch = count
			matches = 0
			firstLine = True
			count += 1

	if maxMatches > 0:
		topic = topMatch
		printResponse(getTopic())
	else:
		if uIn[-1:] == '?':
			topic = 1
			printResponse(getTopic())
		else:
			topic = 0
			printResponse(getTopic())

#gets next response
def getResponse(uIn=""):
	if '?' in uIn:
		findTopic(uIn)
	else:
		line = convoFile.readline()
		if line != "\n":
			printResponse(line)
		else:
			findTopic(uIn)

# Start of conversation
uIn = ""
print("[Say hi!]\n\n")

while "bye" not in uIn:
	if uIn != "":
		if saveAnswer == True:
			tagged = findAnswer(uIn, lastType)
			memory[lastAnswer] = tagged
			saveAnswer = False
		getResponse(uIn.lower())
		print()

	uIn = input()
	print()

printResponse("\nSee you soon!")

exit()