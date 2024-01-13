class Solution:
    def digitSum(self, s: str, k: int) -> str:
        if len(s) <= k:
            return s
        t = []
        while s:
            t.append(str(sum(int(v) for v in s[:k])))
            s = s[k:]
        return self.digitSum(''.join(t), k)
