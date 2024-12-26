class Solution:
    def numTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        def cal(nums: List[int], cnt: Counter) -> int:
            ans = 0
            for x in nums:
                for y, v1 in cnt.items():
                    z = x * x // y
                    if y * z == x * x:
                        v2 = cnt[z]
                        ans += v1 * (v2 - int(y == z))
            return ans // 2

        cnt1 = Counter(nums1)
        cnt2 = Counter(nums2)
        return cal(nums1, cnt2) + cal(nums2, cnt1)
