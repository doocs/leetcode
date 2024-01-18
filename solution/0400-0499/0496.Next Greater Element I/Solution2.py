class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        m = {}
        stk = []
        for v in nums2[::-1]:
            while stk and stk[-1] <= v:
                stk.pop()
            if stk:
                m[v] = stk[-1]
            stk.append(v)
        return [m.get(x, -1) for x in nums1]
