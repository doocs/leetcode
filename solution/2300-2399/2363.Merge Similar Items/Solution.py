class Solution:
    def mergeSimilarItems(
        self, items1: List[List[int]], items2: List[List[int]]
    ) -> List[List[int]]:
        cnt = [0] * 1010
        for v, w in chain(items1, items2):
            cnt[v] += w
        return [[i, v] for i, v in enumerate(cnt) if v]
