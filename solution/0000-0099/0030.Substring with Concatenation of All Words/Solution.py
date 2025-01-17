class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        cnt = Counter(words)
        m, n = len(s), len(words)
        k = len(words[0])
        ans = []
        for i in range(k):
            l = r = i
            cnt1 = Counter()
            while r + k <= m:
                t = s[r : r + k]
                r += k
                if cnt[t] == 0:
                    l = r
                    cnt1.clear()
                    continue
                cnt1[t] += 1
                while cnt1[t] > cnt[t]:
                    rem = s[l : l + k]
                    l += k
                    cnt1[rem] -= 1
                if r - l == n * k:
                    ans.append(l)
        return ans
