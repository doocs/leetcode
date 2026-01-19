class Solution:
    def lexSmallestAfterDeletion(self, s: str) -> str:
        cnt = Counter(s)
        stk = []
        for c in s:
            while stk and stk[-1] > c and cnt[stk[-1]] > 1:
                cnt[stk.pop()] -= 1
            stk.append(c)
        while stk and cnt[stk[-1]] > 1:
            cnt[stk.pop()] -= 1
        return "".join(stk)
