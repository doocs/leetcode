class Solution:
    def minimumString(self, a: str, b: str, c: str) -> str:
        def f(s: str, t: str) -> str:
            if s in t:
                return t
            if t in s:
                return s
            p = t + "#" + s + "$"
            n = len(p)
            next = [0] * n
            next[0] = -1
            i, j = 2, 0
            while i < n:
                if p[i - 1] == p[j]:
                    j += 1
                    next[i] = j
                    i += 1
                elif j:
                    j = next[j]
                else:
                    next[i] = 0
                    i += 1
            return s + t[next[-1] :]

        ans = ""
        for a, b, c in permutations((a, b, c)):
            s = f(f(a, b), c)
            if ans == "" or len(s) < len(ans) or (len(s) == len(ans) and s < ans):
                ans = s
        return ans
