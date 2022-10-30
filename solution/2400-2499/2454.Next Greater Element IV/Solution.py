class Solution:
    def secondGreaterElement(self, nums: List[int]) -> List[int]:
        stk = []
        q = []
        ans = [-1] * len(nums)
        for i, v in enumerate(nums):
            while q and q[0][0] < v:
                ans[q[0][1]] = v
                heappop(q)
            while stk and nums[stk[-1]] < v:
                heappush(q, (nums[stk[-1]], stk.pop()))
            stk.append(i)
        return ans
