# class Solution:
#     def minWindow(self, s: str, t: str) -> str:
#         m, n = len(s), len(t)
#         if n > m:
#             return ""
#         need, window = defaultdict(int), defaultdict(int)
#         for c in t:
#             need[c] += 1
#         start, minLen = 0, inf
#         left, right = 0, 0
#         while right < m:
#             window[s[right]] += 1
#             right += 1
#             while self.check(need, window):
#                 if right - left < minLen:
#                     minLen = right - left
#                     start = left
#                 window[s[left]] -= 1
#                 left += 1
#         return "" if minLen == inf else s[start:start + minLen]

#     def check(self, need, window):
#         for k, v in need.items():
#             if window[k] < v:
#                 return False
#         return True


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
