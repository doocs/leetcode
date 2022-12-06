class Solution:
    def kthSmallest(self, mat: List[List[int]], k: int) -> int:
        pre = [0]
        for cur in mat:
            t = [a + b for a in pre for b in cur[:k]]
            t.sort()
            pre = t[:k]
        return pre[k - 1]
