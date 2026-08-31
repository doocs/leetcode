class Solution:
    def maxActiveSectionsAfterTrade(
        self, s: str, queries: List[List[int]]
    ) -> List[int]:
        n = len(s)
        active = s.count('1')
        if '0' not in s:
            return [active] * len(queries)

        zeros = []
        idx = [0] * n
        for i in range(n):
            if s[i] == '0':
                if i and s[i - 1] == '0':
                    zeros[-1][1] += 1
                else:
                    zeros.append([i, 1])
            idx[i] = len(zeros) - 1

        m = len(zeros) - 1
        K = m.bit_length() if m else 0
        st = [[0] * max(m, 0) for _ in range(max(K, 1))]
        for i in range(m):
            st[0][i] = zeros[i][1] + zeros[i + 1][1]
        for k in range(1, K):
            for i in range(m - (1 << k) + 1):
                st[k][i] = max(st[k - 1][i], st[k - 1][i + (1 << (k - 1))])

        def query(l: int, r: int) -> int:
            if l > r or m <= 0:
                return 0
            k = (r - l + 1).bit_length() - 1
            return max(st[k][l], st[k][r - (1 << k) + 1])

        ans = []
        for L, R in queries:
            iL, iR = idx[L], idx[R]
            cntL = -1 if iL < 0 else zeros[iL][1] - (L - zeros[iL][0])
            cntR = -1 if iR < 0 else R - zeros[iR][0] + 1
            start = iL + 1
            end = iR - (s[R] == '0')
            best = active
            if start < end:
                best = max(best, active + query(start, end - 1))
            if s[L] == '0' and s[R] == '0' and iL + 1 == iR:
                best = max(best, active + cntL + cntR)
            if s[L] == '0' and iL + 1 < iR + (s[R] == '1'):
                best = max(best, active + cntL + zeros[iL + 1][1])
            if s[R] == '0' and iL < iR - 1:
                best = max(best, active + cntR + zeros[iR - 1][1])
            ans.append(best)
        return ans
