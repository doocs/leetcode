class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        j, n = 0, len(trainers)
        for i, p in enumerate(players):
            while j < n and trainers[j] < p:
                j += 1
            if j == n:
                return i
            j += 1
        return len(players)
