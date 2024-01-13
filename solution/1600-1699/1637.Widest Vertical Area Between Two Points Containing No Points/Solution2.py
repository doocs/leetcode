class Solution:
    def maxWidthOfVerticalArea(self, points: List[List[int]]) -> int:
        nums = [x for x, _ in points]
        n = len(nums)
        mi, mx = min(nums), max(nums)
        bucket_size = max(1, (mx - mi) // (n - 1))
        bucket_count = (mx - mi) // bucket_size + 1
        buckets = [[inf, -inf] for _ in range(bucket_count)]
        for x in nums:
            i = (x - mi) // bucket_size
            buckets[i][0] = min(buckets[i][0], x)
            buckets[i][1] = max(buckets[i][1], x)
        ans = 0
        prev = inf
        for curmin, curmax in buckets:
            if curmin > curmax:
                continue
            ans = max(ans, curmin - prev)
            prev = curmax
        return ans
