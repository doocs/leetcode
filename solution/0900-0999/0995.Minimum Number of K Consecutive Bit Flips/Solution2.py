class Solution:
    def minKBitFlips(self, nums: List[int], k: int) -> int:
        ans = flipped = 0
        for i, x in enumerate(nums):
            if i >= k and nums[i - k] == -1:
                flipped ^= 1
            if x == flipped:
                if i + k > len(nums):
                    return -1
                flipped ^= 1
                ans += 1
                nums[i] = -1
        return ans
