class Solution:
    def mostPopularCreator(self, creators: List[str], ids: List[str], views: List[int]) -> List[List[str]]:
        ans = []
        cnt = Counter()
        d = defaultdict(int)
        x = defaultdict(str)
        for c, idx, view in zip(creators, ids, views):
            cnt[c] += view
            if c not in d or d[c] < view or (d[c] == view and x[c] > idx):
                d[c] = view
                x[c] = idx
        ans = []
        pre = -1
        for a, b in cnt.most_common():
            if b >= pre:
                pre = b
                ans.append([a, x[a]])
            else:
                break
        return ans
