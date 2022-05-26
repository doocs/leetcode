class Solution:
    def longestCommonPrefix(self, strs: List[str]) -> str:
        n = len(strs)
        if n == 0:
            return ''
        for i in range(len(strs[0])):
            for j in range(1, n):
                if len(strs[j]) <= i or strs[j][i] != strs[0][i]:
                    return strs[0][:i]
        return strs[0]
