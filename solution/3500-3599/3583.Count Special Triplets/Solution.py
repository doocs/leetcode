class Solution:
    def specialTriplets(self, nums: List[int]) -> int:
        left = Counter()
        right = Counter(nums)
        ans = 0
        mod = 10**9 + 7
        for x in nums:
            right[x] -= 1
            ans = (ans + left[x * 2] * right[x * 2] % mod) % mod
            left[x] += 1
        return ans
