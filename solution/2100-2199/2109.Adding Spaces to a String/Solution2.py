class Solution:
    def addSpaces(self, s: str, spaces: List[int]) -> str:
        ans = []
        i, j = len(s) - 1, len(spaces) - 1
        while i >= 0:
            ans.append(s[i])
            if j >= 0 and i == spaces[j]:
                ans.append(' ')
                j -= 1
            i -= 1
        return ''.join(ans[::-1])
