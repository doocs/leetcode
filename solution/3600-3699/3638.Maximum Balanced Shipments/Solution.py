class Solution:
    def maxBalancedShipments(self, weight: List[int]) -> int:
        ans = mx = 0
        for x in weight:
            mx = max(mx, x)
            if x < mx:
                ans += 1
                mx = 0
        return ans
