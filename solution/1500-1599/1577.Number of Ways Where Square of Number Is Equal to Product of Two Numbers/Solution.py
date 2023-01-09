class Solution:
    def numTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        cnt1 = Counter(nums1)
        cnt2 = Counter(nums2)
        ans = 0
        for a, x in cnt1.items():
            for b, y in cnt2.items():
                if a * a % b == 0:
                    c = a * a // b
                    if b == c:
                        ans += x * y * (y - 1)
                    else:
                        ans += x * y * cnt2[c]
                if b * b % a == 0:
                    c = b * b // a
                    if a == c:
                        ans += x * (x - 1) * y
                    else:
                        ans += x * y * cnt1[c]
        return ans >> 1
