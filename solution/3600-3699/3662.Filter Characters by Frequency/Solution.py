class Solution:
    def filterCharacters(self, s: str, k: int) -> str:
        cnt = Counter(s)
        ans = []
        for c in s:
            if cnt[c] < k:
                ans.append(c)
        return "".join(ans)
