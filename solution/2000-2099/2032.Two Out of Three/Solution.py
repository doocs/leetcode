class Solution:
    def twoOutOfThree(
        self, nums1: List[int], nums2: List[int], nums3: List[int]
    ) -> List[int]:
        s1, s2, s3 = set(nums1), set(nums2), set(nums3)
        ans = []
        for i in range(1, 101):
            a, b, c = i in s1, i in s2, i in s3
            if a + b + c > 1:
                ans.append(i)
        return ans
