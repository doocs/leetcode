class Solution:
    def minimizeMaxDifference(self, nums: List[int]) -> int:
        left, right, result = 0, int(1e9), int(1e9)
        x, y = 0, 0

        def isValid(maxDiff):
            nonlocal x, y
            prev = nums[0]
            minVal, maxVal = 1, int(1e9)
            for i in range(1, len(nums)):
                if nums[i] == -1:
                    minVal = max(prev - maxDiff, 1)
                    maxVal = min(prev + maxDiff, int(1e9))
                    if minVal > maxVal:
                        return False
                    prev = (minVal + maxVal) // 2
                else:
                    if prev != -1 and abs(nums[i] - prev) > maxDiff:
                        return False
                    prev = nums[i]
            x, y = minVal, maxVal
            return True

        while left <= right:
            mid = left + (right - left) // 2
            if isValid(mid):
                result = mid
                right = mid - 1
            else:
                left = mid + 1

        print(f"Optimal pair: x={x}, y={y}")
        return result
