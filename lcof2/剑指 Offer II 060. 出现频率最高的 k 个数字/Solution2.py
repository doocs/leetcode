class Solution:
    def topKFrequent(self, nums: list[int], k: int) -> list[int]:
        cnt = Counter(nums)
        hp = []
        for num, freq in cnt.items():
            heappush(hp, (freq, num))
            if len(hp) > k:
                heappop(hp)
        return [v[1] for v in hp]
