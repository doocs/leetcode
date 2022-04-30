class Solution:
    def findContestMatch(self, n: int) -> str:
        team = [str(i + 1) for i in range(n)]
        while n > 1:
            for i in range(n >> 1):
                team[i] = f'({team[i]},{team[n - 1 - i]})'
            n >>= 1
        return team[0]
