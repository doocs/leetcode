class Solution:
    def areSimilar(self, mat: List[List[int]], k: int) -> bool:
        n = len(mat[0])
        for i, row in enumerate(mat):
            for j, x in enumerate(row):
                if i % 2 == 1 and x != mat[i][(j + k) % n]:
                    return False
                if i % 2 == 0 and x != mat[i][(j - k + n) % n]:
                    return False
        return True
