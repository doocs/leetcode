class Solution:
    def findDisappearedNumbers(
        self, nums: List[int], lower: int, upper: int
    ) -> List[List[int]]:
        ans = []
        prev = lower - 1
        for x in sorted(set(nums)):
            if x < lower:
                continue
            if x > upper:
                break
            if x - prev > 1:
                ans.append([prev + 1, x - 1])
            prev = x
        if prev < upper:
            ans.append([prev + 1, upper])
        return ans
