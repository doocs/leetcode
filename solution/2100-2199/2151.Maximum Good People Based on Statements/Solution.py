class Solution:
    def maximumGood(self, statements: List[List[int]]) -> int:
        def check(k):
            cnt = 0
            for i, s in enumerate(statements):
                if (k >> i) & 1:
                    for j in range(n):
                        if s[j] < 2 and ((k >> j) & 1) != s[j]:
                            return 0
                    cnt += 1
            return cnt

        n = len(statements)
        return max(check(k) for k in range(1 << n))
