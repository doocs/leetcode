class Solution:
    def mostFrequent(self, nums: List[int], key: int) -> int:
        cnt = Counter()
        ans = mx = 0
        for a, b in pairwise(nums):
            if a == key:
                cnt[b] += 1
                if mx < cnt[b]:
                    mx = cnt[b]
                    ans = b
        return ans
