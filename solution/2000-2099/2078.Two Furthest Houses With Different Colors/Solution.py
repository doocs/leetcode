class Solution:
    def maxDistance(self, colors: List[int]) -> int:
        ans, n = 0, len(colors)
        for i in range(n):
            for j in range(i + 1, n):
                if colors[i] != colors[j]:
                    ans = max(ans, abs(i - j))
        return ans
