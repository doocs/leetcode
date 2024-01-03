class Solution:
    def canMakePalindromeQueries(self, s: str, queries: List[List[int]]) -> List[bool]:
        def count(pre: List[List[int]], i: int, j: int) -> List[int]:
            return [x - y for x, y in zip(pre[j + 1], pre[i])]

        def sub(cnt1: List[int], cnt2: List[int]) -> List[int]:
            res = []
            for x, y in zip(cnt1, cnt2):
                if x - y < 0:
                    return []
                res.append(x - y)
            return res

        def check(
            pre1: List[List[int]], pre2: List[List[int]], a: int, b: int, c: int, d: int
        ) -> bool:
            if diff[a] > 0 or diff[m] - diff[max(b, d) + 1] > 0:
                return False
            if d <= b:
                return count(pre1, a, b) == count(pre2, a, b)
            if b < c:
                return (
                    diff[c] - diff[b + 1] == 0
                    and count(pre1, a, b) == count(pre2, a, b)
                    and count(pre1, c, d) == count(pre2, c, d)
                )
            cnt1 = sub(count(pre1, a, b), count(pre2, a, c - 1))
            cnt2 = sub(count(pre2, c, d), count(pre1, b + 1, d))
            return bool(cnt1) and bool(cnt2) and cnt1 == cnt2

        n = len(s)
        m = n // 2
        t = s[m:][::-1]
        s = s[:m]
        pre1 = [[0] * 26 for _ in range(m + 1)]
        pre2 = [[0] * 26 for _ in range(m + 1)]
        diff = [0] * (m + 1)
        for i, (c1, c2) in enumerate(zip(s, t), 1):
            pre1[i] = pre1[i - 1][:]
            pre2[i] = pre2[i - 1][:]
            pre1[i][ord(c1) - ord("a")] += 1
            pre2[i][ord(c2) - ord("a")] += 1
            diff[i] = diff[i - 1] + int(c1 != c2)
        ans = []
        for a, b, c, d in queries:
            c, d = n - 1 - d, n - 1 - c
            ok = (
                check(pre1, pre2, a, b, c, d)
                if a <= c
                else check(pre2, pre1, c, d, a, b)
            )
            ans.append(ok)
        return ans
