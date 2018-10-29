# 24ms with python2
class Solution(object):
    def isValid(self, s):
        dict = {"]":"[",")":"(","}":"{"}  #利用字典匹配储存
        stack = []  #栈
        for char in s:  #遍历每一个字符
            if char in dict.values():  #如果是左边的标点
                stack.append(char)   #入栈
            elif char in dict.keys():     #如果是右边的符号
                if stack == [] or dict[char] != stack.pop():
                    return False    #如果栈里空着，或者和栈里顺序不匹配就返回F
            else:
                return False
        return stack == []     #判断栈里是否空着
        
