import nltk
import pickle
from nltk.corpus import state_union
from nltk.tokenize import PunktSentenceTokenizer
from nltk.tokenize import word_tokenize

if __name__ == '__main__':
	train_text = state_union.raw("2005-GWBush.txt")
	custom_sent_tokenizer = PunktSentenceTokenizer(train_text)
	with open('sent_tokenizer.pickle', 'wb') as f:
		pickle.dump(custom_sent_tokenizer, f)


def tagNtokenize(strInput):
	with open('sent_tokenizer.pickle', 'rb') as f:
		custom_sent_tokenizer = pickle.load(f)

	with open('root_dict.pickle', 'rb') as f:
		root_dict = pickle.load(f)


	tokenized = custom_sent_tokenizer.tokenize(strInput.lower())
	tagged = []
	try:
		for t in tokenized:
			words2 = nltk.word_tokenize(t)
			for i in range(0, len(words2)-1):
				if(words2[i] in root_dict):
					words2[i] = root_dict[words2[i]]
			tagged = nltk.pos_tag(words2)
	except Exception as e:
		print(str(e))

	return tagged
