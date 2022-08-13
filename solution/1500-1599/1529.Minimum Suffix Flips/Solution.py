class Solution:
    def minFlips(self, target: str) -> int:
        ans = 0
        for v in target:
            if (ans & 1) ^ int(v):
                ans += 1
        return ans
