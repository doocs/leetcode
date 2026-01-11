class Solution:
    def maximumAND(self, nums: List[int], k: int, m: int) -> int:
        mx = (max(nums) + k).bit_length()
        ans = 0
        cost = [0] * len(nums)
        for bit in range(mx - 1, -1, -1):
            target = ans | (1 << bit)
            for i, x in enumerate(nums):
                j = (target & ~x).bit_length()
                mask = (1 << j) - 1
                cost[i] = (target & mask) - (x & mask)
            cost.sort()
            if sum(cost[:m]) <= k:
                ans = target
        return ans
