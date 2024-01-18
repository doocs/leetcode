class Solution:
    def robotWithString(self, s: str) -> str:
        n = len(s)
        right = [chr(ord('z') + 1)] * (n + 1)
        for i in range(n - 1, -1, -1):
            right[i] = min(s[i], right[i + 1])
        ans = []
        stk = []
        for i, c in enumerate(s):
            stk.append(c)
            while stk and stk[-1] <= right[i + 1]:
                ans.append(stk.pop())
        return ''.join(ans)
