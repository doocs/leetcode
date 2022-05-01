class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        n = len(nums)
        s = set()
        for i in range(n):
            cnt = 0
            t = ""
            for j in range(i, n):
                if nums[j] % p == 0:
                    cnt += 1
                if cnt <= k:
                    t += str(nums[j]) + ","
                    s.add(t)
                else:
                    break
        return len(s)
