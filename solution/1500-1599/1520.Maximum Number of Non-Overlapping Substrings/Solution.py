class Solution:
    def maxNumOfSubstrings(self, s: str) -> List[str]:
        first, last = {}, {}
        for i, c in enumerate(s):
            if c not in first:
                first[c] = i
            last[c] = i
        segs = []
        for l in first.values():
            r, i = last[s[l]], l
            while i <= r:
                if first[s[i]] < l:
                    break
                r = max(r, last[s[i]])
                i += 1
            if i > r:
                segs.append((l, r))
        segs.sort(key=lambda x: x[1])
        ans, end = [], -1
        for l, r in segs:
            if l > end:
                ans.append(s[l : r + 1])
                end = r
        return ans
