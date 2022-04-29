class Solution:
    def waysToSplit(self, nums: List[int]) -> int:
        mod = 1e9 + 7
        n = len(nums)
        pre = [0] * (n + 1)
        for i in range(1, n + 1):
            pre[i] = pre[i - 1] + nums[i - 1]
        ans = 0
        for i in range(1, n - 1):
            if pre[i] * 3 > pre[n]:
                break
            left, right = i + 1, n - 1
            while left < right:
                mid = (left + right + 1) >> 1
                if pre[mid] - pre[i] <= pre[n] - pre[mid]:
                    left = mid
                else:
                    right = mid - 1
            mid_right = left
            left, right = i + 1, n - 1
            while left < right:
                mid = (left + right) >> 1
                if pre[mid] - pre[i] >= pre[i]:
                    right = mid
                else:
                    left = mid + 1
            ans += (mid_right - left + 1) % mod
        return int(ans % mod)
