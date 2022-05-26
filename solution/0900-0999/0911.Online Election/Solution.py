class TopVotedCandidate:
    def __init__(self, persons: List[int], times: List[int]):
        mx = cur = 0
        counter = Counter()
        self.times = times
        self.wins = []
        for i, p in enumerate(persons):
            counter[p] += 1
            if counter[p] >= mx:
                mx, cur = counter[p], p
            self.wins.append(cur)

    def q(self, t: int) -> int:
        left, right = 0, len(self.wins) - 1
        while left < right:
            mid = (left + right + 1) >> 1
            if self.times[mid] <= t:
                left = mid
            else:
                right = mid - 1
        return self.wins[left]


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)
