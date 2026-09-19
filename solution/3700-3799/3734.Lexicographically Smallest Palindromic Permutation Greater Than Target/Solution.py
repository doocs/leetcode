class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        def build(left: str, middle: str, n: int) -> str:
            right = left[::-1]
            if n % 2:
                return left + middle + right
            return left + right

        n = len(s)
        freq = [0] * 26
        for c in s:
            freq[ord(c) - 97] += 1
        odd = 0
        middle = ""
        for i, v in enumerate(freq):
            if v % 2:
                odd += 1
                middle = chr(97 + i)
        if odd > 1:
            return ""

        half = [v // 2 for v in freq]
        half_len = n // 2
        target_half = target[:half_len]
        remaining = half[:]
        prefix = []
        matched = 0
        for i in range(half_len):
            x = ord(target_half[i]) - 97
            if remaining[x] == 0:
                break
            prefix.append(target_half[i])
            remaining[x] -= 1
            matched += 1

        if matched == half_len:
            cand = build("".join(prefix), middle, n)
            if cand > target:
                return cand

        last = half_len - 1 if matched == half_len else matched
        for pos in range(last, -1, -1):
            rem = half[:]
            valid = True
            for i in range(pos):
                x = ord(target_half[i]) - 97
                if rem[x] == 0:
                    valid = False
                    break
                rem[x] -= 1
            if not valid:
                continue
            target_char = ord(target_half[pos]) - 97
            for c in range(target_char + 1, 26):
                if rem[c] == 0:
                    continue
                left = target_half[:pos] + chr(97 + c)
                rem[c] -= 1
                for x in range(26):
                    left += chr(97 + x) * rem[x]
                    rem[x] = 0
                cand = build(left, middle, n)
                if cand > target:
                    return cand
                rem = half[:]
                for i in range(pos):
                    rem[ord(target_half[i]) - 97] -= 1
        return ""
