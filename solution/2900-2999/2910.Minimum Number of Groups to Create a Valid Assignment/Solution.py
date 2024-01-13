class Solution:
    def minGroupsForValidAssignment(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        for k in range(min(cnt.values()), 0, -1):
            ans = 0
            for v in cnt.values():
                if v // k < v % k:
                    ans = 0
                    break
                ans += (v + k) // (k + 1)
            if ans:
                return ans
