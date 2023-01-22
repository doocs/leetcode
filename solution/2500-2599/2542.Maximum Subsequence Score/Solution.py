class Solution:
    def maxScore(self, nums1: List[int], nums2: List[int], k: int) -> int:
        nums = sorted(zip(nums2, nums1), reverse=True)
        q = []
        ans = s = 0
        for a, b in nums:
            s += b
            heappush(q, b)
            if len(q) == k:
                ans = max(ans, s * a)
                s -= heappop(q)
        return ans
