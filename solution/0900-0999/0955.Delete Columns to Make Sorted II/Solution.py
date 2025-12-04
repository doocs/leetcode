class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        n = len(strs)
        m = len(strs[0])
        st = [False] * (n - 1)
        ans = 0
        for j in range(m):
            must_del = False
            for i in range(n - 1):
                if not st[i] and strs[i][j] > strs[i + 1][j]:
                    must_del = True
                    break
            if must_del:
                ans += 1
            else:
                for i in range(n - 1):
                    if not st[i] and strs[i][j] < strs[i + 1][j]:
                        st[i] = True
        return ans
