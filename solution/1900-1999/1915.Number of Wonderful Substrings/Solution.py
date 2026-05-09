class Solution:
    def wonderfulSubstrings(self, word: str) -> int:
        cnt = defaultdict(int)
        cnt[0] = 1
        ans = st = 0
        for c in word:
            st ^= 1 << (ord(c) - ord("a"))
            ans += cnt[st]
            ans += sum(cnt[st ^ (1 << i)] for i in range(10))
            cnt[st] += 1
        return ans
