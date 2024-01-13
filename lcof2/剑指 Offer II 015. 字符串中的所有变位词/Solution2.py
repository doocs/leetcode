class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        m, n = len(s), len(p)
        if m < n:
            return []
        cnt = Counter()
        for a, b in zip(s, p):
            cnt[a] += 1
            cnt[b] -= 1
        diff = sum(x != 0 for x in cnt.values())
        ans = []
        if diff == 0:
            ans.append(0)
        for i in range(n, m):
            a, b = s[i - n], s[i]
            if cnt[a] == 0:
                diff += 1
            cnt[a] -= 1
            if cnt[a] == 0:
                diff -= 1
            if cnt[b] == 0:
                diff += 1
            cnt[b] += 1
            if cnt[b] == 0:
                diff -= 1
            if diff == 0:
                ans.append(i - n + 1)
        return ans
