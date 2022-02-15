class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        n1, n2 = len(s1), len(s2)
        if n1 > n2:
            return False
        window = [0 for _ in range(26)]
        for i in range(n1):
            window[ord(s1[i]) - ord('a')] += 1
            window[ord(s2[i]) - ord('a')] -= 1
        if self.check(window):
            return True
        for i in range(n1, n2):
            window[ord(s2[i]) - ord('a')] -= 1
            window[ord(s2[i - n1]) - ord('a')] += 1
            if self.check(window):
                return True
        return False

    def check(self, window: List[int]) -> bool:
        return all([cnt == 0 for cnt in window])
