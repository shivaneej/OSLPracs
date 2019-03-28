# E > x+T
# T > (E) | x
#eliminate left recursion or common left factor
n = 0
def e():
  global n
  ret = False
  if(ip.count('(')!=ip.count(')')):
    return False
  if ip[n]=='x' and ip[n+1]=='+':
    n+=2
    ret = True if (t()) else False
  return ret
    
def t():
  global n
  ret = True
  if ip[n]=='(':
    n+=1
    ret = True if (e()) else False
  elif ip[n]==')':
    n+=1
  elif ip[n]=='x':
    n+=1
  else:
    ret = False
  return ret

ip = input('Enter input string\n')
if(e()):
  print("Valid string")
else:
  print("Invalid String")
