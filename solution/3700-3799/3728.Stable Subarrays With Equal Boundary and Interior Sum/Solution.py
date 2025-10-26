class Solution:
    def countStableSubarrays(self, capacity: List[int]) -> int:
        s = list(accumulate(capacity, initial=0))
        n = len(capacity)
        ans = 0
        cnt = defaultdict(int)
        for r in range(2, n):
            l = r - 2
            cnt[(capacity[l], capacity[l] + s[l + 1])] += 1
            ans += cnt[(capacity[r], s[r])]
        return ans
