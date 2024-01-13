class Solution:
    def minWindow(self, s: str, t: str) -> str:
        m, n = len(s), len(t)
        if n > m:
            return ""
        need, window = defaultdict(int), defaultdict(int)
        needCount, windowCount = 0, 0
        for c in t:
            if need[c] == 0:
                needCount += 1
            need[c] += 1
        start, minLen = 0, inf
        left, right = 0, 0
        while right < m:
            ch = s[right]
            right += 1
            if ch in need:
                window[ch] += 1
                if window[ch] == need[ch]:
                    windowCount += 1
            while windowCount == needCount:
                if right - left < minLen:
                    minLen = right - left
                    start = left
                ch = s[left]
                left += 1
                if ch in need:
                    if window[ch] == need[ch]:
                        windowCount -= 1
                    window[ch] -= 1
        return "" if minLen == inf else s[start : start + minLen]
