class Solution:
    def twoEditWords(self, queries: list[str], dictionary: list[str]) -> list[str]:
        ans = []
        for s in queries:
            for t in dictionary:
                if sum(a != b for a, b in zip(s, t)) < 3:
                    ans.append(s)
                    break
        return ans
