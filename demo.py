import tagNtokenize
import correlation_cf as cf
import time
import pickle

with open('question_answers.pickle', 'rb') as f:
        question_answers= pickle.load(f)

sub = ""
while(True):
        uIn = input("Enter text: ")
##        uIn2 = input("Enter text 2: ")
        ##print(uIn)
        ##uIn = "Do you have a favorite team?"
        ##uIn2 = "What is your favorite team?"

        current_milli_time = lambda: int(round(time.time() * 1000))

        tagtime = current_milli_time()
        tagged = tagNtokenize.tagNtokenize(uIn)
        tagtime = current_milli_time() - tagtime
##        tagged2 = tagNtokenize.tagNtokenize(uIn2)

        max_u = 0.6
        str_ans = "Sorry I don't quite know how to answer that!"

        corrtime = current_milli_time()

        # If in subquestions
        if(sub != ""):
                for quest in question_answers[sub]:
##                      print("\n"+quest)
                      tagged2 = tagNtokenize.tagNtokenize(quest)
                      temp_u = cf.correlate(tagged,tagged2)
##                      print("u= " + str(temp_u))
                      if(temp_u>max_u):
                              try:
                                      str_ans = question_answers[sub][quest]
##                                      print(tagged2)
                                      max_u=temp_u
                              except:
                                      print("Error in Sub referencing")
                                      sub = ""
                                
        for quest in question_answers:
##                print("\n"+quest)
                tagged2 = tagNtokenize.tagNtokenize(quest)
                temp_u = cf.correlate(tagged,tagged2)
##                print("u= " + str(temp_u))
                if(temp_u>max_u):
                        max_u=temp_u
                        str_ans = question_answers[quest]['ans']
##                        print(tagged2)
                        sub = quest
        corrtime = current_milli_time() - corrtime
        
##        print(str(tagtime)+(" ms - tagtime"))
        ##for t in tagged:
        ##      print(t)
        
##        print(tagged)
##        print(tagged2)
##        print("max_u = " + str(max_u))

##        print("u = " + str(cf.correlate(tagged, tagged2)))
                
##        print(str(corrtime) + (" ms - corrtime"))
        print("\nResponse>> " + str_ans)
