class FindSumPairs:
    def __init__(self, nums1: List[int], nums2: List[int]):
        self.nums1 = nums1
        self.nums2 = nums2
        self.counter = Counter(nums2)

    def add(self, index: int, val: int) -> None:
        old_val = self.nums2[index]
        self.counter[old_val] -= 1
        self.nums2[index] += val
        self.counter[old_val + val] += 1

    def count(self, tot: int) -> int:
        return sum([self.counter[tot - num] for num in self.nums1])


# Your FindSumPairs object will be instantiated and called as such:
# obj = FindSumPairs(nums1, nums2)
# obj.add(index,val)
# param_2 = obj.count(tot)
