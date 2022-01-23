class Solution:
    def maximumGood(self, statements: List[List[int]]) -> int:
        def check(k):
            cnt = 0
            for i in range(n):
                if (k >> i) & 1:
                    for j in range(n):
                        if statements[i][j] < 2 and ((k >> j) & 1) != statements[i][j]:
                            return 0
                    cnt += 1
            return cnt

        n = len(statements)
        return max(check(k) for k in range(1 << n))
