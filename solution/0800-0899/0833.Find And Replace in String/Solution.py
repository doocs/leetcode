class Solution:
    def findReplaceString(
        self, s: str, indices: List[int], sources: List[str], targets: List[str]
    ) -> str:
        n = len(s)
        d = [-1] * n
        for i, (j, source) in enumerate(zip(indices, sources)):
            if s[j : j + len(source)] == source:
                d[j] = i
        ans = []
        i = 0
        while i < n:
            if d[i] >= 0:
                ans.append(targets[d[i]])
                i += len(sources[d[i]])
            else:
                ans.append(s[i])
                i += 1
        return ''.join(ans)
