class Solution:
    def customSortString(self, order: str, s: str) -> str:
        cnt = Counter(s)
        ans = []
        for c in order:
            ans.append(cnt[c] * c)
            cnt[c] = 0
        for c in s:
            ans.append(cnt[c] * c)
            cnt[c] = 0
        return ''.join(ans)
