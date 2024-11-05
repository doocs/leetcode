class Solution:
    def countKConstraintSubstrings(
        self, s: str, k: int, queries: List[List[int]]
    ) -> List[int]:
        cnt = [0, 0]
        i, n = 0, len(s)
        d = [n] * n
        pre = [0] * (n + 1)
        for j, x in enumerate(map(int, s)):
            cnt[x] += 1
            while cnt[0] > k and cnt[1] > k:
                d[i] = j
                cnt[int(s[i])] -= 1
                i += 1
            pre[j + 1] = pre[j] + j - i + 1
        ans = []
        for l, r in queries:
            p = min(r + 1, d[l])
            a = (1 + p - l) * (p - l) // 2
            b = pre[r + 1] - pre[p]
            ans.append(a + b)
        return ans
