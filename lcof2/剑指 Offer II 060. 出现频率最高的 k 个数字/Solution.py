class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        counter = Counter(nums)
        hp = []
        for num, freq in counter.items():
            if len(hp) == k:
                heapq.heappush(hp, (freq, num))
                heapq.heappop(hp)
            else:
                heapq.heappush(hp, (freq, num))
        return [t[1] for t in hp]
