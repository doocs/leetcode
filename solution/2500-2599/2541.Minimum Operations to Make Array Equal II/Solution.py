class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int], k: int) -> int:
        a = b = 0
        for x, y in zip(nums1, nums2):
            if x == y:
                continue
            if k == 0 or (x - y) % k:
                return -1
            t = (x - y) // k
            if t < 0:
                a += -t
            else:
                b += t
        return a if a == b else -1
