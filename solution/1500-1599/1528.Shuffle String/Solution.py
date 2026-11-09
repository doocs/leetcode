class Solution:
    def restoreString(self, s: str, indices: list[int]) -> str:
        ans = [None] * len(s)
        for c, j in zip(s, indices):
            ans[j] = c
        return "".join(ans)
