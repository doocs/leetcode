class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        stk = []
        mp = {}
        for num in nums2[::-1]:
            while stk and stk[-1] <= num:
                stk.pop()
            mp[num] = stk[-1] if stk else -1
            stk.append(num)
        return [mp[num] for num in nums1]
