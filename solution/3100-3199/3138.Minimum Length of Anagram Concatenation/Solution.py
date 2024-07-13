class Solution:
    def minAnagramLength(self, s: str) -> int:
        def check(k: int) -> bool:
            for i in range(0, n, k):
                cnt1 = Counter(s[i : i + k])
                for c, v in cnt.items():
                    if cnt1[c] * (n // k) != v:
                        return False
            return True

        cnt = Counter(s)
        n = len(s)
        for i in range(1, n + 1):
            if n % i == 0 and check(i):
                return i
