class Solution:
    def maxNumberOfApples(self, weight: List[int]) -> int:
        weight.sort()
        ans = 0
        t = 0
        for v in weight:
            if t + v > 5000:
                break
            t += v
            ans += 1
        return ans
