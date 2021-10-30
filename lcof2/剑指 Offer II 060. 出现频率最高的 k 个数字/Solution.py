class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        counter = Counter(nums)

        hp = []
        for num, freq in counter.items():
            if len(hp) == k:
                if freq > hp[0][0]:
                    heapq.heappop(hp)
                    heapq.heappush(hp, (freq, num))
            else:
                heapq.heappush(hp, (freq, num))

        return list(map(lambda t: t[1], hp))
