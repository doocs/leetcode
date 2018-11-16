import operator as op

class Solution:
    def evalRPN(self, tokens):
        """
        :type tokens: list[str]
        :rtype: int
        """
        stack = []
        Opr = {"+":op.add, "-":op.sub, "*":op.mul, "/":op.truediv}
        
        for i in tokens:
            if i in Opr:
                stack.append( int( Opr[i](stack.pop(-2), stack.pop(-1)) ) )
            else:
                stack.append(int(i))
        
        return stack[0]
