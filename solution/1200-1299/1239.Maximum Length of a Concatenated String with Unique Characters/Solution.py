class Solution:
    def maxLength(self, arr: List[str]) -> int:
        ans = 0
        masks = [0]
        for s in arr:
            mask = 0
            for c in s:
                i = ord(c) - ord('a')
                if mask >> i & 1:
                    mask = 0
                    break
                mask |= 1 << i
            if mask == 0:
                continue
            for m in masks:
                if m & mask == 0:
                    masks.append(m | mask)
                    ans = max(ans, (m | mask).bit_count())
        return ans
