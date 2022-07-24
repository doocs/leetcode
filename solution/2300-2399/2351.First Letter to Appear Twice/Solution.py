class Solution:
    def repeatedCharacter(self, s: str) -> str:
        cnt = Counter()
        for v in s:
            cnt[v] += 1
            if cnt[v] == 2:
                return v
