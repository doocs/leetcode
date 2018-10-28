# 80ms with python2
class Solution(object):
    def romanToInt(self, s):
        dict = {'M':1000, 'D':500, 'C':100, 'L':50, 'X':10, 'V':5, 'I':1}   #对应关系存在一个字典里
        sum = 0
        for i in range(len(s)-1):
            if dict[s[i]] < dict[s[i+1]]:   #这里小于是因为只会有一个减的存在，换句话说如果两个一样的出现，应该是加法
                sum -= dict[s[i]]
            else:
                sum += dict[s[i]]
        return sum+dict[s[-1]]   #最后一位一定是加
