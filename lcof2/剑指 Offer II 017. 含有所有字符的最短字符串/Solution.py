class Solution:
    def minWindow(self, s: str, t: str) -> str:
        m, n = len(s), len(t)
        if n > m:
            return ""
        need, window = defaultdict(int), defaultdict(int)
        for c in t:
            need[c] += 1
        start, minLen = 0, inf
        left, right = 0, 0
        while right < m:
            window[s[right]] += 1
            right += 1
            while self.check(need, window):
                if right - left < minLen:
                    minLen = right - left
                    start = left
                window[s[left]] -= 1
                left += 1
        return "" if minLen == inf else s[start : start + minLen]

    def check(self, need, window):
        for k, v in need.items():
            if window[k] < v:
                return False
        return True
