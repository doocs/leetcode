'''
分词判断即可
'''


class Solution:
    def findOcurrences(self, text: str, first: str, second: str) -> List[str]:
        ls = text.split()
        loc = 0
        ans = []
        while loc < len(ls) - 2:
            if ls[loc] == first and ls[loc + 1] == second:
                ans.append(ls[loc + 2])
            loc += 1
        return ans
