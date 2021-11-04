class TopVotedCandidate:
    def __init__(self, persons: List[int], times: List[int]):
        self.times = times
        self.persons = persons
        n = len(persons)
        win_person = [0] * n
        count = [0] * n
        cur_max = -1
        cur_win = -1
        for i in range(n):
            count[persons[i]] += 1
            if count[persons[i]] >= cur_max:
                cur_win = persons[i]
                cur_max = count[persons[i]]
            win_person[i] = cur_win
        self.win_person = win_person

    def q(self, t: int) -> int:
        times = self.times
        win_person = self.win_person
        left, right = 0, len(times) - 1
        while left < right:
            mid = (left + right + 1) // 2
            if times[mid] <= t:
                left = mid
            else:
                right = mid - 1
        return win_person[left]