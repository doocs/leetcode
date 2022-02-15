class Solution:
    def circularArrayLoop(self, nums: List[int]) -> bool:
        n = len(nums)

        def next(i):
            return (i + nums[i] % n + n) % n

        for i in range(n):
            if nums[i] == 0:
                continue
            slow, fast = i, next(i)
            while nums[slow] * nums[fast] > 0 and nums[slow] * nums[next(fast)] > 0:
                if slow == fast:
                    if slow != next(slow):
                        return True
                    break
                slow, fast = next(slow), next(next(fast))
            j = i
            while nums[j] * nums[next(j)] > 0:
                nums[j] = 0
                j = next(j)
        return False
