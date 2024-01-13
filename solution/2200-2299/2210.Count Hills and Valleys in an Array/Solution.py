class Solution:
    def countHillValley(self, nums: List[int]) -> int:
        arr = [nums[0]]
        for v in nums[1:]:
            if v != arr[-1]:
                arr.append(v)
        return sum(
            (arr[i] < arr[i - 1]) == (arr[i] < arr[i + 1])
            for i in range(1, len(arr) - 1)
        )
