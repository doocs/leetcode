class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        need, window = {}, {}
        validate, left, right = 0, 0, 0
        for c in s1:
            window[c] = 0
            if c in need:
                need[c] += 1
            else:
                need[c] = 1
        # sliding window
        for right in range(len(s2)):
            c = s2[right]
            if c in need:
                window[c] += 1
                if window[c] == need[c]:
                    validate += 1
            while right - left + 1 >= len(s1):
                if validate == len(need):
                    return True
                d = s2[left]
                left += 1
                if d in need:
                    if window[d] == need[d]:
                        validate -= 1
                    window[d] -= 1
        return False
