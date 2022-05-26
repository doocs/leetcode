class Solution:
    def maxNumberOfFamilies(self, n: int, reservedSeats: List[List[int]]) -> int:
        m = defaultdict(int)
        for i, j in reservedSeats:
            m[i] = m[i] | (1 << (10 - j))
        masks = (0b0111100000, 0b0000011110, 0b0001111000)
        ans = (n - len(m)) << 1
        for v in m.values():
            for mask in masks:
                if (v & mask) == 0:
                    v |= mask
                    ans += 1
        return ans
