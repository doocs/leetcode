class Solution:
    def goodIndices(self, s: str) -> List[int]:
        ans = []
        for i in range(len(s)):
            t = str(i)
            k = len(t)
            if s[i + 1 - k : i + 1] == t:
                ans.append(i)
        return ans
