class Solution:
    def minAbsDifference(self, nums: List[int], goal: int) -> int:
        def dfs(arr, res, i, s):
            if i == len(arr):
                res.add(s)
                return
            dfs(arr, res, i + 1, s)
            dfs(arr, res, i + 1, s + arr[i])

        n = len(nums)
        left, right = set(), set()
        dfs(nums[: n >> 1], left, 0, 0)
        dfs(nums[n >> 1 :], right, 0, 0)
        right = sorted(right)
        ans = inf
        for l in left:
            x = goal - l
            i = bisect_left(right, x)
            if i < len(right):
                ans = min(ans, abs(x - right[i]))
            if i:
                ans = min(ans, abs(x - right[i - 1]))
        return ans
