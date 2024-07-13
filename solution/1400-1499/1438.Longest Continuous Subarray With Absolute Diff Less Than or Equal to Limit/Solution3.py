class Solution:
    def longestSubarray(self, nums: List[int], limit: int) -> int:
        maxq = deque()
        minq = deque()
        l, n = 0, len(nums)
        for r, x in enumerate(nums):
            while maxq and nums[maxq[-1]] < x:
                maxq.pop()
            while minq and nums[minq[-1]] > x:
                minq.pop()
            maxq.append(r)
            minq.append(r)
            if nums[maxq[0]] - nums[minq[0]] > limit:
                l += 1
                if maxq[0] < l:
                    maxq.popleft()
                if minq[0] < l:
                    minq.popleft()
        return n - l
