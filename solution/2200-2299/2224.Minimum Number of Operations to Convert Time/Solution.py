class Solution:
    def convertTime(self, current: str, correct: str) -> int:
        a = int(current[:2]) * 60 + int(current[3:])
        b = int(correct[:2]) * 60 + int(correct[3:])
        ans, d = 0, b - a
        for i in [60, 15, 5, 1]:
            ans += d // i
            d %= i
        return ans
