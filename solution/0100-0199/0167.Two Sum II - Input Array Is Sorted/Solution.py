class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        i, j = 1, len(numbers)
        while i < j:
            x = numbers[i - 1] + numbers[j - 1]
            if x == target:
                return [i, j]
            if x < target:
                i += 1
            else:
                j -= 1
        return [-1, -1]
