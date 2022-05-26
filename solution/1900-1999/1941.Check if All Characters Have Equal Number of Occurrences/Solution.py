class Solution:
    def areOccurrencesEqual(self, s: str) -> bool:
        counter = Counter(s)
        cnt = -1
        for c, times in counter.items():
            if cnt == -1:
                cnt = times
            elif cnt != times:
                return False
        return True
