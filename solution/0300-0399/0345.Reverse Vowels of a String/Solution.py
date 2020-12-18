class Solution:
    def reverseVowels(self, s: str) -> str:
        if s is None:
            return s
        chars = list(s)
        p, q = 0, len(chars) - 1
        while p < q:
            if chars[p] not in 'aeiouAEIOU':
                p += 1
                continue
            if chars[q] not in 'aeiouAEIOU':
                q -= 1
                continue
            chars[p], chars[q] = chars[q], chars[p]
            p += 1
            q -= 1
        return ''.join(chars)
