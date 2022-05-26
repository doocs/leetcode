class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
       # concatenate the 2 lists and sort them
        nums1 += nums2
        nums1.sort()
        length = len(nums1)
        value = length/2
        if length % 2 == 0:
            value = int(value)
            return (nums1[value-1] + nums1[value])/2
        else:
            return nums1[int(value)]
