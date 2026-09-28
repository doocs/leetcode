class Solution:
    def maxEarnings(self, meetings: list[list[int]]) -> int:
        n = len(meetings)
        meetings.sort(key=lambda x: x[1])

        pre_max = [-inf] * (n + 1)
        ans = 0

        for i, (start, end, revenue) in enumerate(meetings):
            val = revenue
            if start >= meetings[0][1]:
                j = bisect_right(meetings, start, hi=i, key=lambda x: x[1])
                val += pre_max[j] + start

            ans = max(ans, val)
            pre_max[i + 1] = max(pre_max[i], val - end)

        return ans
