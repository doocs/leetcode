class Solution:
    def mirrorFrequency(self, s: str) -> int:
        freq = Counter(s)
        ans = 0
        vis = set()
        for c, v in freq.items():
            m = (
                chr(ord("a") + 25 - (ord(c) - ord("a")))
                if c.isalpha()
                else str(9 - int(c))
            )
            if m in vis:
                continue
            vis.add(c)
            ans += abs(v - freq[m])
        return ans
