class Solution:
    def minOperations(self, nums: list[int], k: int) -> int:
        for i, v in enumerate(nums):
            nums[i] = v % k
        ans = inf
        for x in range(k):
            for y in range(k):
                if x != y:
                    cnt = 0
                    for i, v in enumerate(nums):
                        target = x if i % 2 == 0 else y
                        diff = abs(target - v)
                        cnt += min(diff, k - diff)
                    ans = min(ans, cnt)
        return ans
