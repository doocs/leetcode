class Solution:
    def restoreString(self, s: str, indices: List[int]) -> str:
        ans = [0] * len(s)
        for i, c in enumerate(s):
            ans[indices[i]] = c
        return ''.join(ans)
