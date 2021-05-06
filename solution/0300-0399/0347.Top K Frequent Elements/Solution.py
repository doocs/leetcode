class Solution:
    def topKFrequent(self, nums: List[int], k: int) -> List[int]:
        counter = collections.Counter(nums)
        buckets = [[] for _ in range(len(nums) + 1)]
        for num, freq in counter.items():
            buckets[freq].append(num)
        i, res = len(nums), []
        while k > 0 and i >= 0:
            if buckets[i]:
                for num in buckets[i]:
                    if k <= 0:
                        break
                    res.append(num)
                    k -= 1
            i -= 1
        return res
