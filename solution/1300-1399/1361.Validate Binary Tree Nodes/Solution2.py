class Solution:
    def validateBinaryTreeNodes(
        self, n: int, leftChild: List[int], rightChild: List[int]
    ) -> bool:
        indeg = [0] * n
        for c in chain(leftChild, rightChild):
            if c != -1:
                indeg[c] += 1
        root = next((i for i, x in enumerate(indeg) if x == 0), -1)
        if root == -1:
            return False
        q = deque([root])
        vis = {root}
        while q:
            i = q.popleft()
            for j in (leftChild[i], rightChild[i]):
                if j != -1:
                    if j in vis:
                        return False
                    vis.add(j)
                    q.append(j)
        return len(vis) == n
