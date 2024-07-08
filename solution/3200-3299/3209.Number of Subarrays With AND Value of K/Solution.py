class Solution:
    def countSubarrays(self, nums: List[int], k: int) -> int:
        ans = 0
        pre = Counter()
        for x in nums:
            cur = Counter()
            for y, v in pre.items():
                cur[x & y] += v
            cur[x] += 1
            ans += cur[k]
            pre = cur
        return ans
