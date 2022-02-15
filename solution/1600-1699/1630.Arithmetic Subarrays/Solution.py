class Solution:
    def checkArithmeticSubarrays(
        self, nums: List[int], l: List[int], r: List[int]
    ) -> List[bool]:
        def check(nums, l, r):
            if r - l < 2:
                return True
            s = set(nums[l : r + 1])
            mx = max(nums[l : r + 1])
            mi = min(nums[l : r + 1])
            if (mx - mi) % (r - l) != 0:
                return False
            delta = (mx - mi) / (r - l)
            for i in range(1, r - l + 1):
                if (mi + delta * i) not in s:
                    return False
            return True

        return [check(nums, l[i], r[i]) for i in range(len(l))]
