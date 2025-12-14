class Solution:
    def minMoves(self, balance: List[int]) -> int:
        if sum(balance) < 0:
            return -1
        mn = min(balance)
        if mn >= 0:
            return 0
        need = -mn
        i = balance.index(mn)
        n = len(balance)
        ans = 0
        for j in range(1, n):
            a = balance[(i - j + n) % n]
            b = balance[(i + j - n) % n]
            c1 = min(a, need)
            need -= c1
            ans += c1 * j
            c2 = min(b, need)
            need -= c2
            ans += c2 * j
        return ans
