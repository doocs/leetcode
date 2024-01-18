class Solution:
    def wonderfulSubstrings(self, word: str) -> int:
        cnt = Counter({0: 1})
        ans = st = 0
        for c in word:
            st ^= 1 << (ord(c) - ord("a"))
            ans += cnt[st]
            for i in range(10):
                ans += cnt[st ^ (1 << i)]
            cnt[st] += 1
        return ans
