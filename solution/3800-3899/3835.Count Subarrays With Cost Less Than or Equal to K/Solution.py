class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        ans = 0
        q1 = deque()
        q2 = deque()
        l = 0
        for r, x in enumerate(nums):
            while q1 and nums[q1[-1]] <= x:
                q1.pop()
            while q2 and nums[q2[-1]] >= x:
                q2.pop()
            q1.append(r)
            q2.append(r)
            while l < r and (nums[q1[0]] - nums[q2[0]]) * (r - l + 1) > k:
                l += 1
                if q1[0] < l:
                    q1.popleft()
                if q2[0] < l:
                    q2.popleft()
            ans += r - l + 1
        return ans
