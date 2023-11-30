class Solution:
    def minKBitFlips(self, nums: List[int], k: int) -> int:
        n = len(nums)
        d = [0] * (n + 1)
        ans = s = 0
        for i, x in enumerate(nums):
            s += d[i]
            if x % 2 == s % 2:
                if i + k > n:
                    return -1
                d[i] += 1
                d[i + k] -= 1
                s += 1
                ans += 1
        return ans
