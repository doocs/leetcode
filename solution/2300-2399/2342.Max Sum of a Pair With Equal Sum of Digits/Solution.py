class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        d = defaultdict(list)
        for i, v in enumerate(nums):
            t = 0
            while v:
                t += v % 10
                v //= 10
            d[t].append(nums[i])
        ans = -1
        for v in d.values():
            v.sort(reverse=True)
            if len(v) > 1:
                ans = max(ans, v[0] + v[1])
        return ans
