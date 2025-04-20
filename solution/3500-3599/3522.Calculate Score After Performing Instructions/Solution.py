class Solution:
    def calculateScore(self, instructions: List[str], values: List[int]) -> int:
        n = len(values)
        vis = [False] * n
        ans = i = 0
        while 0 <= i < n and not vis[i]:
            vis[i] = True
            if instructions[i][0] == "a":
                ans += values[i]
                i += 1
            else:
                i = i + values[i]
        return ans
