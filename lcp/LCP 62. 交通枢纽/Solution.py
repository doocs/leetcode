class Solution:
    def transportationHub(self, path: List[List[int]]) -> int:
        ind = Counter()
        outd = Counter()
        s = set()
        vis = set()
        for a, b in path:
            if (a, b) in vis:
                continue
            vis.add((a, b))
            s.add(a)
            s.add(b)
            outd[a] += 1
            ind[b] += 1
        for c in s:
            if ind[c] == len(s) - 1 and outd[c] == 0:
                return c
        return -1
