class Solution:
    def pathInZigZagTree(self, label: int) -> List[int]:
        x = i = 1
        while (x << 1) <= label:
            x <<= 1
            i += 1
        ans = [0] * i
        while i:
            ans[i - 1] = label
            label = ((1 << (i - 1)) + (1 << i) - 1 - label) >> 1
            i -= 1
        return ans
