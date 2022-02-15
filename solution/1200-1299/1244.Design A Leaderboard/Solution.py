class Leaderboard:
    def __init__(self):
        self.player_scores = {}

    def addScore(self, playerId: int, score: int) -> None:
        self.player_scores[playerId] = self.player_scores.get(playerId, 0) + score

    def top(self, K: int) -> int:
        scores = sorted(list(self.player_scores.values()), reverse=True)
        return sum(scores[:K])

    def reset(self, playerId: int) -> None:
        self.player_scores[playerId] = 0


# Your Leaderboard object will be instantiated and called as such:
# obj = Leaderboard()
# obj.addScore(playerId,score)
# param_2 = obj.top(K)
# obj.reset(playerId)
