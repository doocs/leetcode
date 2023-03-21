class Solution:
    def countTriplets(self, nums: List[int]) -> int:
        cnt = Counter(x & y for x in nums for y in nums)
        return sum(v for xy, v in cnt.items() for z in nums if xy & z == 0)
