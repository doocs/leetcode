class Solution:
    def mergeSimilarItems(
        self, items1: list[list[int]], items2: list[list[int]]
    ) -> list[list[int]]:
        cnt = Counter()
        for v, w in chain(items1, items2):
            cnt[v] += w
        return [[v, w] for v, w in sorted(cnt.items())]
