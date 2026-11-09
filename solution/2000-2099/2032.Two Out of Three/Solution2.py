class Solution:
    def twoOutOfThree(
        self, nums1: list[int], nums2: list[int], nums3: list[int]
    ) -> list[int]:
        mask = defaultdict(int)
        for i, nums in enumerate((nums1, nums2, nums3)):
            for x in nums:
                mask[x] |= 1 << i
        return [x for x, v in mask.items() if v & (v - 1)]
