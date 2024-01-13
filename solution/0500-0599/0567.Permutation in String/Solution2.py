class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        n, m = len(s1), len(s2)
        if n > m:
            return False
        cnt = Counter()
        for a, b in zip(s1, s2):
            cnt[a] -= 1
            cnt[b] += 1
        diff = sum(x != 0 for x in cnt.values())
        if diff == 0:
            return True
        for i in range(n, m):
            a, b = s2[i - n], s2[i]

            if cnt[b] == 0:
                diff += 1
            cnt[b] += 1
            if cnt[b] == 0:
                diff -= 1

            if cnt[a] == 0:
                diff += 1
            cnt[a] -= 1
            if cnt[a] == 0:
                diff -= 1

            if diff == 0:
                return True
        return False
