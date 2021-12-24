class Solution:
    def subarraySum(self, nums: List[int], k: int) -> int:
        mp = Counter()
        mp[0] = 1
        res = s = 0
        for num in nums:
            s += num
            res += mp[s - k]
            mp[s] += 1
        return res
