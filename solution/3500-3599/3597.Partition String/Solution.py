class Solution:
    def partitionString(self, s: str) -> List[str]:
        vis = set()
        ans = []
        t = ""
        for c in s:
            t += c
            if t not in vis:
                vis.add(t)
                ans.append(t)
                t = ""
        return ans
