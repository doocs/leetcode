class Solution:
    def minArray(self, numbers: List[int]) -> int:
        l, r = 0, len(numbers) - 1
        while l < r:
            m = l + ((r - l) >> 1)
            if numbers[m] > numbers[r]:
                l = m + 1
            elif numbers[m] < numbers[r]:
                r = m
            else:
                r -= 1
        return numbers[l]
