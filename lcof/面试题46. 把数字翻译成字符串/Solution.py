class Solution:
    def translateNum(self, num: int) -> int:
        def cal(s):
            if len(s) < 2:
                return 1
            t = int(s[:2])
            return cal(s[1:]) if t < 10 or t > 25 else cal(s[1:]) + cal(s[2:])

        return cal(str(num))
