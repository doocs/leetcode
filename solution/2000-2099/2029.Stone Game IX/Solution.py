class Solution:
    def stoneGameIX(self, stones: List[int]) -> bool:
        def check(c):
            if c[1] == 0:
                return False
            c[1] -= 1
            turn = 1 + min(c[1], c[2]) * 2 + c[0]
            if c[1] > c[2]:
                turn += 1
                c[1] -= 1
            return turn % 2 == 1 and c[1] != c[2]

        c = [0] * 3
        for s in stones:
            c[s % 3] += 1
        c1 = [c[0], c[2], c[1]]
        return check(c) or check(c1)
