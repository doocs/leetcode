class Solution:
    def minOperations(self, nums: List[int]) -> int:
        f = {nums[0]: 0}
        for x in nums[1:]:
            g = {}
            for pre, s in f.items():
                cur = (x + pre - 1) // pre * pre
                while cur <= 100:
                    if cur not in g or g[cur] > s + cur - x:
                        g[cur] = s + cur - x
                    cur += pre
            f = g
        return min(f.values())
