class Solution:
    def kthCharacter(self, s: str, k: int) -> str:
        for w in s.split():
            m = (1 + len(w)) * len(w) // 2
            if k == m:
                return " "
            if k > m:
                k -= m + 1
            else:
                cur = 0
                for i in range(len(w)):
                    cur += i + 1
                    if k < cur:
                        return w[i]
