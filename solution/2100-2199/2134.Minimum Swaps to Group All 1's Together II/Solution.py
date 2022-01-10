class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        cnt = nums.count(1)
        n = len(nums)
        s = [0] * ((n << 1) + 1)
        for i in range(n << 1):
            s[i + 1] = s[i] + nums[i % n]
        mx = 0
        for i in range(n << 1):
            j = i + cnt - 1
            if j < (n << 1):
                mx = max(mx, s[j + 1] - s[i])
        return cnt - mx
