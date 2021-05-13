class Solution:
    def maxSumMinProduct(self, nums: List[int]) -> int:
        n = len(nums)
        pre_sum = [0] * (n + 1)
        for i in range(1, n + 1):
            pre_sum[i] = pre_sum[i - 1] + nums[i - 1]

        stack = []
        next_lesser = [n] * n
        for i in range(n):
            while stack and nums[stack[-1]] > nums[i]:
                next_lesser[stack.pop()] = i
            stack.append(i)

        stack = []
        prev_lesser = [-1] * n
        for i in range(n - 1, -1, -1):
            while stack and nums[stack[-1]] > nums[i]:
                prev_lesser[stack.pop()] = i
            stack.append(i)

        res = 0
        for i in range(n):
            start, end = prev_lesser[i], next_lesser[i]
            t = nums[i] * (pre_sum[end] - pre_sum[start + 1])
            res = max(res, t)
        return res % (10 ** 9 + 7)
