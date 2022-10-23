class Solution:
    def subarrayGCD(self, nums: List[int], k: int) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            x = nums[i]
            for j in range(i, n):
                x = gcd(x, nums[j])
                if x == k:
                    ans += 1
        return ans
