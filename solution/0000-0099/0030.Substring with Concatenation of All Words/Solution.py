class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        cnt = Counter(words)
        m, n = len(s), len(words)
        k = len(words[0])
        ans = []
        for i in range(k):
            cnt1 = Counter()
            l = r = i
            t = 0
            while r + k <= m:
                w = s[r : r + k]
                r += k
                if w not in cnt:
                    l = r
                    cnt1.clear()
                    t = 0
                    continue
                cnt1[w] += 1
                t += 1
                while cnt1[w] > cnt[w]:
                    remove = s[l : l + k]
                    l += k
                    cnt1[remove] -= 1
                    t -= 1
                if t == n:
                    ans.append(l)
        return ans
