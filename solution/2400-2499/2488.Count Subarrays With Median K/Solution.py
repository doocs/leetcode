class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        i = next(i for i, v in enumerate(nums) if v == k)
        n = len(nums)
        ans = 1
        d = defaultdict(int)
        mi = mx = 0
        for j in range(i + 1, n):
            if nums[j] < k:
                mi += 1
            else:
                mx += 1
            if 0 <= mx - mi <= 1:
                ans += 1
            d[mx - mi] += 1
        mi = mx = 0
        for j in range(i - 1, -1, -1):
            if nums[j] < k:
                mi += 1
            else:
                mx += 1
            if 0 <= mx - mi <= 1:
                ans += 1
            ans += d[mi - mx] + d[mi - mx + 1]
        return ans
