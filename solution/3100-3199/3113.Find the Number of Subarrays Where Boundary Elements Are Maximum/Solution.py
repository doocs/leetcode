class Solution:
    def numberOfSubarrays(self, nums: List[int]) -> int:
        stk = []
        ans = 0
        for x in nums:
            while stk and stk[-1][0] < x:
                stk.pop()
            if not stk or stk[-1][0] > x:
                stk.append([x, 1])
            else:
                stk[-1][1] += 1
            ans += stk[-1][1]
        return ans
