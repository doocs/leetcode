class Solution:
    def countNoZeroPairs(self, n: int) -> int:
        digits = list(map(int, str(n)))[::-1]
        digits.append(0)  # absorb final carry
        L = len(digits)

        # dp[carry][aliveA][aliveB]
        dp = [[[0] * 2 for _ in range(2)] for _ in range(2)]
        dp[0][1][1] = 1

        for pos in range(L):
            ndp = [[[0] * 2 for _ in range(2)] for _ in range(2)]
            target = digits[pos]
            for carry in range(2):
                for aliveA in range(2):
                    for aliveB in range(2):
                        ways = dp[carry][aliveA][aliveB]
                        if ways == 0:
                            continue

                        if aliveA:
                            A = [(d, 1) for d in range(1, 10)]
                            if pos > 0:
                                A.append((0, 0))  # end number here
                        else:
                            A = [(0, 0)]

                        if aliveB:
                            B = [(d, 1) for d in range(1, 10)]
                            if pos > 0:
                                B.append((0, 0))
                        else:
                            B = [(0, 0)]

                        for da, na in A:
                            for db, nb in B:
                                s = da + db + carry
                                if s % 10 != target:
                                    continue
                                ndp[s // 10][na][nb] += ways
            dp = ndp

        return dp[0][0][0]

    def countPairs(self, n: int) -> int:
        return self.countNoZeroPairs(n)
