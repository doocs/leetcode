class Solution:
    def answerQueries(self, nums: List[int], queries: List[int]) -> List[int]:
        nums.sort()
        s = list(accumulate(nums))
        return [bisect_right(s, q) for q in queries]
