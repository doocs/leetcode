class Solution:
    def earliestFullBloom(self, plantTime: list[int], growTime: list[int]) -> int:
        ans = t = 0
        for pt, gt in sorted(zip(plantTime, growTime), key=lambda x: -x[1]):
            t += pt
            ans = max(ans, t + gt)
        return ans
