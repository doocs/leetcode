class Solution:
    def maxSumMinProduct(self, nums: List[int]) -> int:
        mod = int(1e9) + 7
        n = len(nums)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, v in enumerate(nums):
            while stk and nums[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and nums[stk[-1]] > nums[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        s = [0] + list(accumulate(nums))
        ans = max(v * (s[right[i]] - s[left[i] + 1]) for i, v in enumerate(nums))
        return ans % mod
