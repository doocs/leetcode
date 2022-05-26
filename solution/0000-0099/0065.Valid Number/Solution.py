class Solution:
    def isNumber(self, s):
        """
        :type s: str
        :rtype: bool
        """

        NumberRE = "[\+\-]?((\d+\.?\d*|\d*\.?\d+)(e[\+\-]?\d+)?)"

        s = s.strip()

        Ans = re.match(NumberRE, s)

        if Ans and len(s) == Ans.regs[0][1]:
            return True

        return False
