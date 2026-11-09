class Solution:
    def answerQueries(self, nums: list[int], queries: list[int]) -> list[int]:
        nums.sort()
        s = list(accumulate(nums))
        return [bisect_right(s, q) for q in queries]
