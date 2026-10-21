class Solution:
    def maxSum(self, nums: list[int], length: int, k: int) -> int:
        n = len(nums)
        max_swaps = min(k, length, n - length)
        ans = -inf
        for i in range(n - length + 1):
            internal = sorted(nums[i : i + length])
            external = sorted(nums[:i] + nums[i + length :], reverse=True)
            total = sum(internal)
            for j in range(max_swaps):
                gain = external[j] - internal[j]
                if gain <= 0:
                    break
                total += gain
            ans = max(ans, total)
        return ans
