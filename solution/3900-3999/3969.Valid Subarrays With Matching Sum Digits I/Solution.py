class Solution:
    def countValidSubarrays(self, nums: list[int], x: int) -> int:
        n = len(nums)
        ans = 0
        for l in range(n):
            s = 0
            for r in range(l, n):
                s += nums[r]
                if s % 10 == x and int(str(s)[0]) == x:
                    ans += 1
        return ans
