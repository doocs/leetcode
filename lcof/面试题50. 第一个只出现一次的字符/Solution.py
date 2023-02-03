class Solution:
    def firstUniqChar(self, s: str) -> str:
        cnt = Counter(s)
        for c in s:
            if cnt[c] == 1:
                return c
        return " "
