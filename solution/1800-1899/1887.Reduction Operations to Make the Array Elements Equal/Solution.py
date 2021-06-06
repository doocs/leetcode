class Solution:
    def reductionOperations(self, nums: List[int]) -> int:
        counter = collections.Counter(nums)
        f = res = 0
        n = len(nums)
        for _, v in sorted(counter.items(), key=lambda x: x[0]):
            f += v
            res += (n - f)
        return res
