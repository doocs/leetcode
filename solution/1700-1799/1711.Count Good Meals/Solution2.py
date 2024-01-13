class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 10**9 + 7
        cnt = Counter(deliciousness)
        ans = 0
        for i in range(22):
            s = 1 << i
            for a, m in cnt.items():
                if (b := s - a) in cnt:
                    ans += m * (m - 1) if a == b else m * cnt[b]
        return (ans >> 1) % mod
