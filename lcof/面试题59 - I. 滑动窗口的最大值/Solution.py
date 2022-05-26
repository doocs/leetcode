class Solution:
    def maxSlidingWindow(self, nums: List[int], k: int) -> List[int]:
        q, res = [], []
        for i, num in enumerate(nums):
            while len(q) != 0 and nums[q[-1]] <= num:
                q.pop(-1)
            q.append(i)

            if q[0] == i - k:
                q = q[1:]
            if i >= k - 1:
                res.append(nums[q[0]])
        return res
