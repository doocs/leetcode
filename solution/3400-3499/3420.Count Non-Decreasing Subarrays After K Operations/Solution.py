class Solution:
    def countNonDecreasingSubarrays(self, nums: list[int], k: int) -> int:
        n = len(nums)
        q = deque()
        ans = 0
        cost = 0
        r = n - 1
        for l in range(n - 1, -1, -1):
            x = nums[l]
            while q and nums[q[-1]] <= x:
                s = q.pop()
                end = q[-1] if q else r + 1
                cost += (end - s) * (x - nums[s])
            q.append(l)
            while cost > k:
                cost -= nums[q[0]] - nums[r]
                if q[0] == r:
                    q.popleft()
                r -= 1
            ans += r - l + 1
        return ans
