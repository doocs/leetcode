class Solution:
    def groupThePeople(self, groupSizes: List[int]) -> List[List[int]]:
        g = defaultdict(list)
        for i, x in enumerate(groupSizes):
            g[x].append(i)
        ans = []
        for x, idx in g.items():
            t = []
            for i in idx:
                t.append(i)
                if len(t) == x:
                    ans.append(t)
                    t = []
        return ans
