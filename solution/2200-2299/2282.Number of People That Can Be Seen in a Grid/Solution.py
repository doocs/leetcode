class Solution:
    def seePeople(self, heights: List[List[int]]) -> List[List[int]]:
        def f(nums: List[int]) -> List[int]:
            n = len(nums)
            stk = []
            ans = [0] * n
            for i in range(n - 1, -1, -1):
                while stk and stk[-1] < nums[i]:
                    ans[i] += 1
                    stk.pop()
                if stk:
                    ans[i] += 1
                while stk and stk[-1] == nums[i]:
                    stk.pop()
                stk.append(nums[i])
            return ans

        ans = [f(row) for row in heights]
        m, n = len(heights), len(heights[0])
        for j in range(n):
            add = f([heights[i][j] for i in range(m)])
            for i in range(m):
                ans[i][j] += add[i]
        return ans
