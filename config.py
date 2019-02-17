import pickle

#Converting words to "root" words(ones we use) dictionary
root_words = {
	"athlete":"player",
	"soccer":"football",
	"your":"you",
	"teams":"team",
	"like":"favorite",
	"club":"team",
	"support":"favorite",
	"favourite": "favorite",
	"sports":"sport"
}

question_answers = {
	"What is your favorite sport?": {"ans": "Football is my favourite sport.",
	 "Why do you like it?": "It is the biggest sport in the World.",
	 "What other sport do you like?" : "I like Basketball.", 
	 "Why do you like football?": "It is the biggest sport in the World."},
	"Who is your favorite football player?": {"ans" : "My favourite football player is Ronaldo.",
	 "Where is he from?": "He's Portugese.",
	 "What team does he play for?": "Ronaldo plays for Juventus",
	 "How many trophies did he win?": "Ronaldo won 5 Ballond'ors"},
	"What football club do you support?": {"ans":"I support Arsenal football club.",
	 "Who is their best striker?": "Their greatest striker is Thierry Henry.",
	 "Why do you support them?":"I mirror my creator's bad choices.",
	 "What football team won the most trophies?": "Real Madrid have won 33 La Liga trophies and 13 UEFA Champions League trophies."},
	"Hello, Hi, Greetings" : {"ans": "Hi, Ask me about Sports!",
	 "What sports do you like?": "I like Football, Basketball, and Table Tennis!",
	 "Why do you like football?": "It is the biggest sport in the World."},
	"Why do you like Table Tennis?" : {"ans" : "My guy Fiyin plays really good.", "Do you know any professional table tennis players?": "Nope."} 
}

with open('root_dict.pickle', 'wb') as f:
		pickle.dump(root_words, f)


with open('question_answers.pickle', 'wb') as f:
		pickle.dump(question_answers, f)
