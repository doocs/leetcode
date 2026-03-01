class Solution:
    def minimumOR(self, grid: List[List[int]]) -> int:
        mx = max(map(max, grid))
        m = mx.bit_length()
        ans = 0
        for i in range(m - 1, -1, -1):
            mask = ans | ((1 << i) - 1)
            for row in grid:
                found = False
                for x in row:
                    if (x | mask) == mask:
                        found = True
                        break
                if not found:
                    ans |= 1 << i
                    break
        return ans
