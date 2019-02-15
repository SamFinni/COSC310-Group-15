import tagNtokenize
import correlation_cf as cf
import time

uIn = input("Enter text: ")
uIn2 = input("Enter text 2: ")
##print(uIn)
##uIn = "Do you have a favorite team?"
##uIn2 = "What is you favorite team?"

current_milli_time = lambda: int(round(time.time() * 1000))

tagtime = current_milli_time()
tagged = tagNtokenize.tagNtokenize(uIn)
tagtime = current_milli_time() - tagtime
tagged2 = tagNtokenize.tagNtokenize(uIn2)

print(str(tagtime)+(" ms - tagtime"))
##for t in tagged:
##      print(t)

print(tagged)
print(tagged2)
corrtime = current_milli_time()
print("u = " + str(cf.correlate(tagged,tagged2)))
corrtime = current_milli_time() - corrtime
print(str(corrtime) + (" ms - corrtime"))
