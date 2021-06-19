class Solution:
    def maxLength(self, arr: List[str]) -> int:
        def ones_count(x):
            c = 0
            while x:
                x &= x - 1
                c += 1
            return c

        ans = 0
        masks = [0]
        for s in arr:
            mask = 0
            for ch in s:
                ch = ord(ch) - ord('a')
                if (mask >> ch) & 1 == 1:
                    mask = 0
                    break
                mask |= 1 << ch
            if mask == 0:
                continue
            for m in masks:
                if m & mask == 0:
                    masks.append(m | mask)
                    ans = max(ans, ones_count(m | mask))

        return ans
