class Solution:
    def numTriplets(self, nums1: list[int], nums2: list[int]) -> int:
        def count(nums: list[int]) -> Counter:
            cnt = Counter()
            for j in range(len(nums)):
                for k in range(j + 1, len(nums)):
                    cnt[nums[j] * nums[k]] += 1
            return cnt

        def cal(nums: list[int], cnt: Counter) -> int:
            return sum(cnt[x * x] for x in nums)

        cnt1 = count(nums1)
        cnt2 = count(nums2)
        return cal(nums1, cnt2) + cal(nums2, cnt1)
