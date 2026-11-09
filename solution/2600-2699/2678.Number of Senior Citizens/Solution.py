class Solution:
    def countSeniors(self, details: list[str]) -> int:
        return sum(int(x[11:13]) > 60 for x in details)
