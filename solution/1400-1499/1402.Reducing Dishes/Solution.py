class Solution:
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        satisfaction.sort(reverse=True)
        ans = presum = 0
        for v in satisfaction:
            presum += v
            if presum > 0:
                ans += presum
            else:
                break
        return ans
