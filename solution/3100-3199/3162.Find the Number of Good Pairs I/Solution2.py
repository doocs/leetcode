class Solution:
    def numberOfPairs(self, nums1: List[int], nums2: List[int], k: int) -> int:
        cnt1 = Counter(x // k for x in nums1 if x % k == 0)
        if not cnt1:
            return 0
        cnt2 = Counter(nums2)
        ans = 0
        mx = max(cnt1)
        for x, v in cnt2.items():
            s = sum(cnt1[y] for y in range(x, mx + 1, x))
            ans += s * v
        return ans
