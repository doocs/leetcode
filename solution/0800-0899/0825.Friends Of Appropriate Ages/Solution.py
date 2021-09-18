class Solution:
    def numFriendRequests(self, ages: List[int]) -> int:
        def check(a, b):
            return (0.5 * a + 7 < b) and (a >= b) and (a >= 100 or b <= 100)

        res = 0
        counter = [0] * 121
        for age in ages:
            counter[age] += 1
        for i in range(1, 121):
            n1 = counter[i]
            for j in range(1, 121):
                if check(i, j):
                    n2 = counter[j]
                    res += (n1 * n2)
                    if i == j:
                        res -= n2
        return res
