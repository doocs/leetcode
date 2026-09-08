class Solution:
    def longestStrChain(self, words: List[str]) -> int:
        def check(a: str, b: str) -> bool:
            if len(a) + 1 != len(b):
                return False
            i = 0
            for c in b:
                if i < len(a) and a[i] == c:
                    i += 1
            return i == len(a)

        words.sort(key=len)
        n = len(words)
        f = [1] * n
        for i in range(n):
            for j in range(i):
                if check(words[j], words[i]):
                    f[i] = max(f[i], f[j] + 1)
        return max(f)
