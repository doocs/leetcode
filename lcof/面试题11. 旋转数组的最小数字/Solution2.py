class Solution:
    def minArray(self, numbers: List[int]) -> int:
        l, r = 0, len(numbers) - 1
        while l < r:
            if numbers[l] < numbers[r]:
                return numbers[l]
            mid = (l + r) >> 1
            if numbers[mid] > numbers[l]:
                l = mid + 1
            elif numbers[mid] < numbers[l]:
                r = mid
            else:
                l += 1
        return numbers[l]
