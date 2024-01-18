class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        m, n = len(s1), len(s2)
        if m > n:
            return False
        cnt1 = Counter(s1)
        cnt2 = Counter(s2[:m])
        if cnt1 == cnt2:
            return True
        for i in range(m, n):
            cnt2[s2[i]] += 1
            cnt2[s2[i - m]] -= 1
            if cnt1 == cnt2:
                return True
        return False
