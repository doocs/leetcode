class Solution:
    def canMakeEqual(self, nums: List[int], k: int) -> bool:
        def check(target: int, k: int) -> bool:
            cnt, sign = 0, 1
            for i in range(len(nums) - 1):
                x = nums[i] * sign
                if x == target:
                    sign = 1
                else:
                    sign = -1
                    cnt += 1
            return cnt <= k and nums[-1] * sign == target

        return check(nums[0], k) or check(-nums[0], k)
