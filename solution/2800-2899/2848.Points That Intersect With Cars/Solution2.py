class Solution:
    def numberOfPoints(self, nums: List[List[int]]) -> int:
        d = defaultdict(int)
        for start, end in nums:
            d[start] += 1
            d[end + 1] -= 1
        ans = s = last = 0
        for cur, v in sorted(d.items()):
            if s > 0:
                ans += cur - last
            s += v
            last = cur
        return ans
