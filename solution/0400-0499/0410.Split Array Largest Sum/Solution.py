class Solution:
    def splitArray(self, nums: List[int], m: int) -> int:
        def check(x):
            s, cnt = 0, 1
            for num in nums:
                if s + num > x:
                    cnt += 1
                    s = num
                else:
                    s += num
            return cnt <= m

        left, right = max(nums), sum(nums)
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
