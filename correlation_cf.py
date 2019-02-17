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

	nounsA = []
	adjectA = []
	verbsA = []
	prpA = []

	nounsB = []
	adjectB = []
	verbsB = []
	prpB = []

	for temp in strA:
		if("NN" in temp[1]):
			nounsA.append(temp[0])
		elif("W" in temp[1]):
			nounsA.append(temp[0])
		elif("VB" in temp[1]):
			verbsA.append(temp[0])
		elif("IN" in temp[1]):
			verbsA.append(temp[0])
		elif("JJ" in temp[1]):
			adjectA.append(temp[0])
		elif("PR" in temp[1]):
			prpA.append(temp[0])

	for temp in strB:
		if("NN" in temp[1]):
			nounsB.append(temp[0])
			if(temp[0] in nounsA):
				n += 1
		elif("VB" in temp[1]):
			verbsB.append(temp[0])
			if(temp[0] in verbsA):
				v += 1
		elif("IN" in temp[1]):
			verbsB.append(temp[0])
			if(temp[0] in verbsA):
				v +=1
		elif("JJ" in temp[1]):
			adjectB.append(temp[0])
			if(temp[0] in adjectA):
				j += 1
		elif("PR" in temp[1]):
			prpB.append(temp[0])
			if(temp[0] in prpA):
				p += 1
		elif("W" in temp[1]):
			nounsB.append(temp[0])
			if(temp[0] in nounsA):
				n+=1

	N = len(nounsA)
	V = len(verbsA)
	J = len(adjectA)
	P = len(prpA)
	# u = (div(n,len(nounsA))*(50*len(nounsA)/x) 
	# 	+ div(v,len(verbsA))*(20*len(verbsA)/x) 
	# 	+ div(j,len(adjectA))*20 
	# 	+ div(p,len(prpA))*10)/(100)

	const = 100
	w_n = (const/2)*div(N,len(nounsB))	
	w_j = ((const-w_n)/2)*div(J,len(adjectB))
	w_v = ((const-w_n - w_j)/2)*div(V,len(verbsB)) 
	w_p = ((const-w_n - w_j -w_v)/2)*div(P,len(prpB))
	w_x = w_n + w_j + w_v + w_p

	u = div((div(n,N)*w_n + div(v,V)*w_v + div(j,J)*w_j + div(p,P)*w_p), w_x)
	return u