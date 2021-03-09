class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        low, high = 0, len(numbers) - 1
        while low <= high:
            if numbers[low] + numbers[high] == target:
                return [low + 1, high + 1]
            if numbers[low] + numbers[high] < target:
                low += 1
            else:
                high -= 1
        return [-1, -1]
