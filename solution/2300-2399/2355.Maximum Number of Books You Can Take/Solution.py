class Solution:
    def maximumBooks(self, books: List[int]) -> int:
        nums = [v - i for i, v in enumerate(books)]
        n = len(nums)
        left = [-1] * n
        stk = []
        for i, v in enumerate(nums):
            while stk and nums[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        ans = 0
        dp = [0] * n
        dp[0] = books[0]
        for i, v in enumerate(books):
            j = left[i]
            cnt = min(v, i - j)
            u = v - cnt + 1
            s = (u + v) * cnt // 2
            dp[i] = s + (0 if j == -1 else dp[j])
            ans = max(ans, dp[i])
        return ans
