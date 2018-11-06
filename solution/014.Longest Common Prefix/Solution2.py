class Solution(object):
    def longestCommonPrefix(self, strs):
        if not strs:
            return ""
        for i,group in enumerate(zip(*strs)):#把字符串变成一个字母一个字母的格式
            if len(set(group))>1: #集合里多于一个字符
                return strs[0][:i] #输出0到目前编号位的字符
        else:
                return min(strs) #其他情况

