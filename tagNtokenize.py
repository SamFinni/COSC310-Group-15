import nltk
import pickle
from nltk.corpus import state_union
from nltk.tokenize import PunktSentenceTokenizer
from nltk.tokenize import word_tokenize

#text = input("NOVA: What do you have to say? \n")
if __name__ == '__main__':
	train_text = state_union.raw("2005-GWBush.txt")
	custom_sent_tokenizer = PunktSentenceTokenizer(train_text)
	with open('sent_tokenizer.pickle', 'wb') as f:
		pickle.dump(custom_sent_tokenizer, f)


def tagNtokenize(strInput):
	with open('sent_tokenizer.pickle', 'rb') as f:
		custom_sent_tokenizer = pickle.load(f)

	tokenized = custom_sent_tokenizer.tokenize(strInput.lower())
	tagged = []
	try:
		for t in tokenized:
			words2 = nltk.word_tokenize(t)
			tagged = nltk.pos_tag(words2)
	except Exception as e:
		print(str(e))

	return tagged




# statement = ['subject', 'action', 'object']
# loc_time = ['', '']

# subject_tags = ['WP', 'WP$', 'NN', 'NNS','NNP', 'NNPS',  'WDT', 'PRP', 'WRB']
# action_tags = ['VB', 'VBD', 'VBG', 'VBN', 'VBZ', 'VBP']
# object_tags = ['NN', 'NNS','NNP', 'NNPS', 'PRP']
# time_words = ['today', 'tomorrow', 'week', 'next', 'last', 'monday', 'tuesday', 'wednesday',
#  'thursday', 'friday', 'saturday', 'sunday', 'january', 'february', 'march', 'april', 'may', 'june', 'july', 'august', 'september', 'october', 'november', 'december']

# time_ind = False

# def istime(temp):
# 	if(temp[1] == 'CD'):
# 		return True
# 	elif(temp[0] in time_words):
# 		return True
# 	else:
# 		return False
# def checkNNTag(temp):
# 	return nltk.pos_tag(['He',temp])

# for t in tagged:
# 	tag = t[1]
# 	if(t[1] == 'NN'):
# 		temp =checkNNTag(t[0])
# 		tag = temp[1][1] 

# 	if(time_ind == True):
# 		if(istime(t)):
# 			loc_time[1] += t[0]+ " "
# 			continue
# 		elif((not istime(t)) & (tag != '.')):
# 			loc_time[0] += t[0] + " "
# 			continue

# 	if((tag in subject_tags) & (statement[0] == 'subject')):
# 		statement[0] = t[0]
# 	elif((tag in action_tags) & (statement[1] == 'action')):
# 		statement[1] = t[0]
# 	elif((tag in object_tags) & (statement[2] == 'object')):
# 		statement[2] = t[0]
# 	elif((tag == 'IN') & time_ind):
# 		continue
# 	elif((tag == 'IN')):
# 		time_ind = True
# 	elif(tag == '.'):
# 		time_ind = False

# print('Subject: ' + statement[0])
# print('Action: ' + statement[1])
# print('Object:  ' + statement[2])

# print('Time (if any): ' + loc_time[1])
# print('Location (if any): ' + loc_time[0])

# stop_words = ['of', 'the', 'some', 'a', 'an', 'any']