class Solution:
    def minSelection(
        self, availableIntervals: list[list[int]], targetIntervals: list[list[int]]
    ) -> int:
        availableIntervals.sort(key=lambda x: x[0])
        targetIntervals.sort(key=lambda x: x[0])
        ans = cur = 0
        i = 0
        for l, r in targetIntervals:
            while cur < r:
                uncover = max(l, cur)
                nxt = cur
                while (
                    i < len(availableIntervals) and availableIntervals[i][0] <= uncover
                ):
                    nxt = max(nxt, availableIntervals[i][1])
                    i += 1
                if nxt < uncover or nxt == cur:
                    return -1
                cur = nxt
                ans += 1
        return ans
