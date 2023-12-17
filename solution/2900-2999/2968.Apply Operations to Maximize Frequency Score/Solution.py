class Solution:
    def maxFrequencyScore(self, nums: List[int], k: int) -> int:
        nums.sort()
        s = list(accumulate(nums, initial=0))
        n = len(nums)
        l, r = 0, n
        while l < r:
            mid = (l + r + 1) >> 1
            ok = False
            for i in range(n - mid + 1):
                j = i + mid
                x = nums[(i + j) // 2]
                left = ((i + j) // 2 - i) * x - (s[(i + j) // 2] - s[i])
                right = (s[j] - s[(i + j) // 2]) - ((j - (i + j) // 2) * x)
                if left + right <= k:
                    ok = True
                    break
            if ok:
                l = mid
            else:
                r = mid - 1
        return l
