class Solution:
    def maximumGood(self, statements: List[List[int]]) -> int:
        def check(mask: int) -> int:
            cnt = 0
            for i, row in enumerate(statements):
                if mask >> i & 1:
                    for j, x in enumerate(row):
                        if x < 2 and (mask >> j & 1) != x:
                            return 0
                    cnt += 1
            return cnt

        return max(check(i) for i in range(1, 1 << len(statements)))
