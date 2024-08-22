class Solution:
    def minimumAddedInteger(self, nums1: List[int], nums2: List[int]) -> int:
        def f(x: int) -> bool:
            i = j = cnt = 0
            while i < len(nums1) and j < len(nums2):
                if nums2[j] - nums1[i] != x:
                    cnt += 1
                else:
                    j += 1
                i += 1
            return cnt <= 2

        nums1.sort()
        nums2.sort()
        ans = inf
        for i in range(3):
            x = nums2[0] - nums1[i]
            if f(x):
                ans = min(ans, x)
        return ans
