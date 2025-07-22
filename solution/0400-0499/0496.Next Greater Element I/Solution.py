class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        stk = []
        d = {}
        for x in nums2[::-1]:
            while stk and stk[-1] < x:
                stk.pop()
            if stk:
                d[x] = stk[-1]
            stk.append(x)
        return [d.get(x, -1) for x in nums1]
