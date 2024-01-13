class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        vis = set()
        for c in s:
            if c in vis:
                vis.remove(c)
            else:
                vis.add(c)
        return len(vis) < 2
