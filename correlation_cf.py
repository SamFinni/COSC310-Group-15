
nounsA = []
adjectA = []

verbsA = []
prpA = []
def div(x,y):
	if(y==0):
		return 0
	else:
		return x / y

def correlate(strA, strB):
	#Correlation coefficient
	u = 0
	#Noun/Verb/Adject/Prp counters
	n = 0
	v = 0
	j = 0
	p = 0


	for temp in strA:
		if("NN" in temp[1]):
			nounsA.append(temp[0])
		elif("VB" in temp[1]):
			verbsA.append(temp[0])
		elif("JJ" in temp[1]):
			adjectA.append(temp[0])
		elif("PR" in temp[1]):
			prpA.append(temp[0])

	for temp in strB:
		if("NN" in temp[1]):
			if(temp[0] in nounsA):
				n += 1
		elif("VB" in temp[1]):
			if(temp[0] in verbsA):
				v += 1
		elif("JJ" in temp[1]):
			if(temp[0] in adjectA):
				j += 1
		elif("PR" in temp[1]):
			if(temp[0] in prpA):
				p += 1
	x = len(strA)
	u = (div(n,len(nounsA))*50 + div(v,len(verbsA))*30 + div(j,len(adjectA))*10 + div(p,len(prpA))*10)/100
	return u 