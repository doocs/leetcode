class Solution:
    def sequenceReconstruction(
        self, nums: List[int], sequences: List[List[int]]
    ) -> bool:
        g = defaultdict(list)
        indeg = [0] * len(nums)
        for seq in sequences:
            for a, b in pairwise(seq):
                g[a - 1].append(b - 1)
                indeg[b - 1] += 1
        q = deque([i for i, v in enumerate(indeg) if v == 0])
        while q:
            if len(q) > 1:
                return False
            i = q.popleft()
            for j in g[i]:
                indeg[j] -= 1
                if indeg[j] == 0:
                    q.append(j)
        return True
