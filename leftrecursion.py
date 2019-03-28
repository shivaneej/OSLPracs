# direct recursion works (Enter $ for ε)
'''https://stackoverflow.com/questions/15999916/step-by-step-elimination-of-this-indirect-left-recursion'''
S = input('Enter start symbol:\n')
prods_list={}
rec_list=[]
nonrec_list=[]
terminals = [S]
explored =[]
while terminals:
  S = terminals.pop(0)
  explored.append(S)
  p = input('Enter production\n'+S+' -> ')
  for x in p:
    if x.isupper() and x not in terminals and x not in explored:
      terminals.append(x)
  prods = p.split("|")
  rec = [i for i in prods if i.startswith(S)]
  nonrec = [i for i in prods if not i.startswith(S)]
  prods_list[S]=prods
  rec_list.append(rec)
  nonrec_list.append(nonrec)
if not rec_list:
  print("Left Recursion is not present")
  exit()
print("After elimination of left recursion:\n")
for x in range(len(prods_list)):
  keys = list(prods_list.keys())
  if not rec_list[x]:
    print(keys[x]+" -> "+ "|".join(prods_list.get(keys[x])))
  else:
    new_nonrec = [y+str(keys[x])+"'" for y in nonrec_list[x]]
    print(keys[x]+" -> "+"|".join(new_nonrec))
    new_rec = [y[1:]+str(keys[x])+"'" for y in rec_list[x]]
    new_rec.append("ε")
    print(str(keys[x])+"' -> "+"|".join(new_rec))


