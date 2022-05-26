class Solution:
    def smallestCommonElement(self, mat: List[List[int]]) -> int:
        counter = Counter()
        for row in mat:
            for num in row:
                counter[num] += 1
                if counter[num] == len(mat):
                    return num
        return -1
