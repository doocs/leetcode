class Solution:
    def largestString(self, nums: List[int]) -> List[str]:
        ans = []
        for x in nums:
            s = []
            for j in range(25, -1, -1):
                t = x >> j
                s.append(chr(ord('a') + j) * t)
                x &= (1 << j) - 1
            ans.append(''.join(s))
        return ans
