class Solution:
    def maximumLength(self, nums: List[int]) -> int:
        d = {}
        for num in sorted(nums)[::-1]:
            if num**2 in d and num in d and num != 1:
                d[num] = d[num**2] + 2
            else:
                d[num] = 1
        ones = nums.count(1)
        return max(max(d.values()), ones - (ones % 2 == 0))
        
