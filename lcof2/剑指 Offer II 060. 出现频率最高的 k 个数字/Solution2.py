class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums)
        hp = []
        for num, freq in cnt.items():
            heappush(hp, (freq, num))
            if len(hp) > k:
                heappop(hp)
        return [v[1] for v in hp]
