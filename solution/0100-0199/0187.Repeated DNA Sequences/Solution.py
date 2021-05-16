class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        n = 10
        subs = set()
        res = set()
        for i in range(len(s) - n + 1):
            sub = s[i:i + n]
            if sub in subs:
                res.add(sub)
            subs.add(sub)
        return list(res)
