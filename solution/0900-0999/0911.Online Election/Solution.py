class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        cnt = Counter()
        self.times = times
        self.wins = []
        cur = 0
        for p in persons:
            cnt[p] += 1
            if cnt[cur] <= cnt[p]:
                cur = p
            self.wins.append(cur)

    def q(self, t: int) -> int:
        i = bisect_right(self.times, t) - 1
        return self.wins[i]


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)
