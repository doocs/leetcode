class Solution:
    def intersect(self, nums1: list[int], nums2: list[int]) -> list[int]:
        cnt = Counter(nums1)
        ans = []
        for x in nums2:
            if cnt[x]:
                ans.append(x)
                cnt[x] -= 1
        return ans
