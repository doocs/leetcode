class Solution:
    def findAnagrams(self, s, p):
        """
        :type s: str
        :type p: str
        :rtype: List[int]
        """
        lens=len(s)
        lenp=len(p)
        if lens < lenp:
            return []
        flag1 = [0] * 26
        flag2 = [0] * 26
        for i, x in enumerate(p):
            flag1[ord(x) - 97] += 1
        ans = []
        for i, x in enumerate(s):
            flag2[ord(x)- 97] += 1
            if i >= lenp:
                flag2[ord(s[i - lenp]) - 97] -= 1
            if flag1 == flag2:
                ans.append(i-lenp+1)
        return ans
