class Solution:
    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        cnt = Counter(nums)
        return [v[0] for v in cnt.most_common(k)]
