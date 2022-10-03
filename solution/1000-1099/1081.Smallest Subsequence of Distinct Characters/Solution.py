class Solution:
    def smallestSubsequence(self, s: str) -> str:
        last = defaultdict(int)
        for i, c in enumerate(s):
            last[c] = i
        stk = []
        vis = set()
        for i, c in enumerate(s):
            if c in vis:
                continue
            while stk and stk[-1] > c and last[stk[-1]] > i:
                vis.remove(stk.pop())
            stk.append(c)
            vis.add(c)
        return ''.join(stk)
