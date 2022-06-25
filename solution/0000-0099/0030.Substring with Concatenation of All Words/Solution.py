class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        cnt = Counter(words)
        sublen = len(words[0])
        n, m = len(s), len(words)
        ans = []
        for i in range(sublen):
            cnt1 = Counter()
            l = r = i
            t = 0
            while r + sublen <= n:
                w = s[r : r + sublen]
                r += sublen
                if w not in cnt:
                    l = r
                    cnt1.clear()
                    t = 0
                    continue
                cnt1[w] += 1
                t += 1
                while cnt1[w] > cnt[w]:
                    remove = s[l : l + sublen]
                    l += sublen
                    cnt1[remove] -= 1
                    t -= 1
                if m == t:
                    ans.append(l)
        return ans
