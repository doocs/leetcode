class Solution:
    def isStraight(self, nums: List[int]) -> bool:
        t = [False for _ in range(14)]
        max_val, min_val = 0, 14
        for num in nums:
            if num == 0:
                continue
            if t[num]:
                return False
            t[num] = True
            max_val = max(max_val, num)
            min_val = min(min_val, num)
        return max_val - min_val <= 4
