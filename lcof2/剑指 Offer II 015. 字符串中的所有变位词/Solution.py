class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        m, n = len(s), len(p)
        if n > m:
            return []
        window, ans = [0 for _ in range(26)], []
        for i in range(n):
            window[ord(p[i]) - ord('a')] += 1
            window[ord(s[i]) - ord('a')] -= 1
        if self.check(window):
            ans.append(0)
        for i in range(n, m):
            window[ord(s[i]) - ord('a')] -= 1
            window[ord(s[i - n]) - ord('a')] += 1
            if self.check(window):
                ans.append(i - n + 1)
        return False

    def check(self, window: List[int]) -> bool:
        return all([cnt == 0 for cnt in window])
