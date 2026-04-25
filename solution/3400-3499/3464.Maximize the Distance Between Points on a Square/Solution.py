class Solution:
    def maxDistance(self, side: int, points: List[List[int]], k: int) -> int:
        nums = []
        for x, y in points:
            if x == 0:
                nums.append(y)
            elif y == side:
                nums.append(side + x)
            elif x == side:
                nums.append(side * 3 - y)
            else:
                nums.append(side * 4 - x)
        nums.sort()

        def check(lo: int) -> bool:
            for start in nums:
                end = start + side * 4 - lo
                cur = start
                ok = True
                for _ in range(k - 1):
                    j = bisect_left(nums, cur + lo)
                    if j == len(nums) or nums[j] > end:
                        ok = False
                        break
                    cur = nums[j]
                if ok:
                    return True
            return False

        l, r = 1, side
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
