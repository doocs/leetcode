class FindSumPairs:
    def __init__(self, nums1: List[int], nums2: List[int]):
        self.cnt = Counter(nums2)
        self.nums1 = nums1
        self.nums2 = nums2

    def add(self, index: int, val: int) -> None:
        self.cnt[self.nums2[index]] -= 1
        self.nums2[index] += val
        self.cnt[self.nums2[index]] += 1

    def count(self, tot: int) -> int:
        return sum(self.cnt[tot - x] for x in self.nums1)


# Your FindSumPairs object will be instantiated and called as such:
# obj = FindSumPairs(nums1, nums2)
# obj.add(index,val)
# param_2 = obj.count(tot)
