class Solution:
    def computeSimilarities(self, docs: List[List[int]]) -> List[str]:
        eps = 1e-9
        d = defaultdict(list)
        for i, v in enumerate(docs):
            for x in v:
                d[x].append(i)
        cnt = Counter()
        for ids in d.values():
            n = len(ids)
            for i in range(n):
                for j in range(i + 1, n):
                    cnt[(ids[i], ids[j])] += 1
        ans = []
        for (i, j), v in cnt.items():
            tot = len(docs[i]) + len(docs[j]) - v
            x = v / tot + eps
            ans.append(f'{i},{j}: {x:.4f}')
        return ans
