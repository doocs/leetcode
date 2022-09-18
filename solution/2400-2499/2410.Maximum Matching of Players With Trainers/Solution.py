class Solution:
    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        players.sort()
        trainers.sort()
        ans = j = 0
        for p in players:
            while j < len(trainers) and trainers[j] < p:
                j += 1
            if j < len(trainers):
                ans += 1
                j += 1
        return ans
