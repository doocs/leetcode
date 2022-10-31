class Solution:
    def mostPopularCreator(
        self, creators: List[str], ids: List[str], views: List[int]
    ) -> List[List[str]]:
        cnt = defaultdict(int)
        d = {}
        x = {}
        for c, i, v in zip(creators, ids, views):
            cnt[c] += v
            if c not in d or d[c] < v or (d[c] == v and x[c] > i):
                d[c], x[c] = v, i
        ans = []
        pre = -1
        for a, b in cnt.items():
            if b > pre:
                ans = [[a, x[a]]]
                pre = b
            elif b == pre:
                ans.append([a, x[a]])
        return ans
