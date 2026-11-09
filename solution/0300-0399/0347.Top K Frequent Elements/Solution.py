class Solution:
    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        cnt = Counter(nums)
        return [x for x, _ in cnt.most_common(k)]
