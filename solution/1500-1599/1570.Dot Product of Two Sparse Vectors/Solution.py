class SparseVector:
    def __init__(self, nums: List[int]):
        self.v = {}
        for i, num in enumerate(nums):
            if num != 0:
                self.v[i] = num

    # Return the dotProduct of two sparse vectors
    def dotProduct(self, vec: 'SparseVector') -> int:
        res = 0
        if len(self.v) > len(vec.v):
            self.v, vec.v = vec.v, self.v
        for i, num in self.v.items():
            if i not in vec.v:
                continue
            res += num * vec.v[i]
        return res


# Your SparseVector object will be instantiated and called as such:
# v1 = SparseVector(nums1)
# v2 = SparseVector(nums2)
# ans = v1.dotProduct(v2)
