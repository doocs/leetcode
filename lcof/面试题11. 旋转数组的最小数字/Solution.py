class Solution:
    def minArray(self, numbers: List[int]) -> int:
        if len(numbers) == 1 or numbers[0] < numbers[-1]:
            return numbers[0]
        left, right = 0, len(numbers) - 1
        while (right - left > 1):
            mid = left + ((right - left) >> 1)
            if numbers[mid] == numbers[left] == numbers[right]:
                return min(numbers[left: right + 1])
            if numbers[mid] >= numbers[left]:
                left = mid
            elif numbers[mid] <= numbers[right]:
                right = mid
        return numbers[right]