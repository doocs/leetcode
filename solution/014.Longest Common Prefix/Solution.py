class Solution:
    def longestCommonPrefix(self, strs):
        """
        :type strs: List[str]
        :rtype: str
        """
        if strs == []:
            return ''
        if len(strs) == 1:
            return strs[0]
        strs.sort(key=lambda x : len(x))
        flag=False
        prefix=''
        for j in range(1,len(strs[0])+1):
            prefix=strs[0][:j]
            for i in range(1,len(strs)):
                if prefix == strs[i][:j]:
                    flag=True
                else:
                    flag=False
                if not flag:
                    prefix0=prefix[:-1]
                    return prefix0
                    break
        return prefix