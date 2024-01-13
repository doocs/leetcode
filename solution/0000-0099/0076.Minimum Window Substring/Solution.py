class Solution:
    def minWindow(self, s: str, t: str) -> str:
        need = Counter(t)
        window = Counter()
        cnt, j, k, mi = 0, 0, -1, inf
        for i, c in enumerate(s):
            window[c] += 1
            if need[c] >= window[c]:
                cnt += 1
            while cnt == len(t):
                if i - j + 1 < mi:
                    mi = i - j + 1
                    k = j
                if need[s[j]] >= window[s[j]]:
                    cnt -= 1
                window[s[j]] -= 1
                j += 1
        return '' if k < 0 else s[k : k + mi]
