class Solution:
    def numberOfRounds(self, startTime: str, finishTime: str) -> int:
        def get(s: str) -> int:
            return int(s[:2]) * 60 + int(s[3:])

        start, finish = get(startTime), get(finishTime)
        if start > finish:
            finish += 24 * 60
        start, finish = (start + 14) // 15, finish // 15
        return max(0, finish - start)
