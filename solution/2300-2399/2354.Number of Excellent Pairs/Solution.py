class Solution:
    def countExcellentPairs(self, nums: list[int], k: int) -> int:
        s = set(nums)
        ans = 0
        cnt = Counter()
        for v in s:
            cnt[v.bit_count()] += 1
        for v in s:
            t = v.bit_count()
            for i, x in cnt.items():
                if t + i >= k:
                    ans += x
        return ans
