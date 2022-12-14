class Solution:
    def maximumNumber(self, num: str, change: List[int]) -> str:
        s = list(num)
        for i, c in enumerate(s):
            if change[int(c)] > int(c):
                while i < len(s) and int(s[i]) <= change[int(s[i])]:
                    s[i] = str(change[int(s[i])])
                    i += 1
                break
        return ''.join(s)
