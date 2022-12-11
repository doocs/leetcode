class Solution:
    def minimumTotalCost(self, nums1: List[int], nums2: List[int]) -> int:
        ans = same = 0
        cnt = Counter()
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            if a == b:
                same += 1
                ans += i
                cnt[a] += 1

        m = lead = 0
        for k, v in cnt.items():
            if v * 2 > same:
                m = v * 2 - same
                lead = k
                break
        for i, (a, b) in enumerate(zip(nums1, nums2)):
            if m and a != b and a != lead and b != lead:
                ans += i
                m -= 1
        return -1 if m else ans
