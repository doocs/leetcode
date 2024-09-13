class Solution:
    def minimumTime(self, power: List[int]) -> int:
        n = len(power)
        f = [inf] * (1 << n)
        f[0] = 0
        for mask in range(1, 1 << n):
            gain = mask.bit_count()
            for i, x in enumerate(power):
                if mask >> i & 1:
                    f[mask] = min(f[mask], f[mask ^ (1 << i)] + (x + gain - 1) // gain)
        return f[-1]
