class Solution:
    def maximumGood(self, statements: List[List[int]]) -> int:
        def check(mask):
            cnt = 0
            for i, s in enumerate(statements):
                if (mask >> i) & 1:
                    for j, v in enumerate(s):
                        if v < 2 and ((mask >> j) & 1) != v:
                            return 0
                    cnt += 1
            return cnt

        return max(check(mask) for mask in range(1, 1 << len(statements)))
