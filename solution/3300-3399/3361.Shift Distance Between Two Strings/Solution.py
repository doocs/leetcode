class Solution:
    def shiftDistance(
        self, s: str, t: str, nextCost: List[int], previousCost: List[int]
    ) -> int:
        m = 26
        s1 = [0] * (m << 1 | 1)
        s2 = [0] * (m << 1 | 1)
        for i in range(m << 1):
            s1[i + 1] = s1[i] + nextCost[i % m]
            s2[i + 1] = s2[i] + previousCost[(i + 1) % m]
        ans = 0
        for a, b in zip(s, t):
            x, y = ord(a) - ord("a"), ord(b) - ord("a")
            c1 = s1[y + m if y < x else y] - s1[x]
            c2 = s2[x + m if x < y else x] - s2[y]
            ans += min(c1, c2)
        return ans
