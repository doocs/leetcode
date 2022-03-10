class Solution:
    def wonderfulSubstrings(self, word: str) -> int:
        counter = Counter({0: 1})
        state = 0
        ans = 0
        for c in word:
            state ^= 1 << (ord(c) - ord('a'))
            ans += counter[state]
            for i in range(10):
                ans += counter[state ^ (1 << i)]
            counter[state] += 1
        return ans
