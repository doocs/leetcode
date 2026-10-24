class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        j = 0
        for t in trainers:
            if j < len(players) and players[j] <= t:
                j += 1
        return j
