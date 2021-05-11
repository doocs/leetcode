class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        stack = []
        mapper = {}
        for num in nums2:
            while stack and stack[-1] < num:
                mapper[stack.pop()] = num
            stack.append(num)
        return [mapper.get(num, -1) for num in nums1]
