class Solution:
    def maximumsSplicedArray(self, nums1: List[int], nums2: List[int]) -> int:
        def f(nums1, nums2):
            d = [a - b for a, b in zip(nums1, nums2)]
            t = mx = d[0]
            for v in d[1:]:
                if t > 0:
                    t += v
                else:
                    t = v
                mx = max(mx, t)
            return mx

        s1, s2 = sum(nums1), sum(nums2)
        return max(s2 + f(nums1, nums2), s1 + f(nums2, nums1))
