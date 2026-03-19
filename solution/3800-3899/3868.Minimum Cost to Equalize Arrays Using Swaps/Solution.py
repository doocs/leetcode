class Solution:
    def minCost(self, nums1: list[int], nums2: list[int]) -> int:
        cnt2 = Counter(nums2)
        cnt1 = Counter()
        for x in nums1:
            if cnt2[x]:
                cnt2[x] -= 1
            else:
                cnt1[x] += 1
        ans = 0
        for v in cnt1.values():
            if v % 2 == 1:
                return -1
            ans += v // 2
        for v in cnt2.values():
            if v % 2 == 1:
                return -1
        return ans
