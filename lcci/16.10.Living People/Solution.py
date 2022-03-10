class Solution:
    def maxAliveYear(self, birth: List[int], death: List[int]) -> int:
        years = [0] * 101
        for i in range(len(birth)):
            start = birth[i] - 1900
            end = death[i] - 1900
            for j in range(start, end + 1):
                years[j] += 1
        max_v = years[0]
        res = 0
        for i in range(1, 101):
            if years[i] > max_v:
                max_v = years[i]
                res = i
        return 1900 + res
