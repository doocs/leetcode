class Solution:
    def maximumXor(self, s: str, t: str) -> str:
        cnt = [0, 0]
        for c in t:
            cnt[int(c)] += 1
        ans = ['0'] * len(s)
        for i, c in enumerate(s):
            x = int(c)
            if cnt[x ^ 1]:
                cnt[x ^ 1] -= 1
                ans[i] = '1'
            else:
                cnt[x] -= 1
        return ''.join(ans)
