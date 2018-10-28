#36ms with python2
class Solution:
    def reverse(self, x):
        s = cmp(x,0)       #提取此输入数字的符号
        r = int(`x*s`[::-1])      #先作为字符串输入，然后转回整型
        return r*s * (r<2**31)          #防止溢出，小于的情况下返回true，否则为false
     

