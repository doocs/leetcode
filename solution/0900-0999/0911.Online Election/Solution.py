class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        self.times = times
        mx, cur_win, n = -1, -1, len(persons)
        counter = [0] * (n + 1)
        self.win_persons = [0] * n
        for i, p in enumerate(persons):
            counter[p] += 1
            if counter[p] >= mx:
                mx = counter[p]
                cur_win = p
            self.win_persons[i] = cur_win

    def q(self, t: int) -> int:
        left, right = 0, len(self.win_persons) - 1
        while left < right:
            mid = (left + right + 1) >> 1
            if self.times[mid] <= t:
                left = mid
            else:
                right = mid - 1
        return self.win_persons[left]

# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)