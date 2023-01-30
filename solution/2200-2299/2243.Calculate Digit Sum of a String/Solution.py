class Solution:
    def digitSum(self, s: str, k: int) -> str:
        while len(s) > k:
            t = []
            n = len(s)
            for i in range(0, n, k):
                x = 0
                for j in range(i, min(i + k, n)):
                    x += int(s[j])
                t.append(str(x))
            s = "".join(t)
        return s
