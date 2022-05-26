class Solution:
    def totalStrength(self, strength: List[int]) -> int:
        n = len(strength)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, v in enumerate(strength):
            while stk and strength[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            while stk and strength[stk[-1]] > strength[i]:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)

        ss = list(accumulate(list(accumulate(strength, initial=0)), initial=0))
        mod = int(1e9) + 7
        ans = 0
        for i, v in enumerate(strength):
            l, r = left[i] + 1, right[i] - 1
            a = (ss[r + 2] - ss[i + 1]) * (i - l + 1)
            b = (ss[i + 1] - ss[l]) * (r - i + 1)
            ans = (ans + (a - b) * v) % mod
        return ans
