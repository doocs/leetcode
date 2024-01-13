class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        m, n = len(s), len(p)
        if m < n:
            return []
        cnt1 = Counter(s[:n])
        cnt2 = Counter(p)
        ans = []
        if cnt1 == cnt2:
            ans.append(0)
        for i in range(n, m):
            cnt1[s[i]] += 1
            cnt1[s[i - n]] -= 1
            if cnt1 == cnt2:
                ans.append(i - n + 1)
        return ans
