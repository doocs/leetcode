class Solution:
    def frequencySort(self, s: str) -> str:
        if not s or len(s) < 3:
            return s
        counter = collections.Counter(s)
        buckets = [[] for _ in range(len(s) + 1)]
        for c, freq in counter.items():
            buckets[freq].append(c)
        res = []
        for i in range(len(s), -1, -1):
            if buckets[i]:
                for c in buckets[i]:
                    res.append(c * i)
        return ''.join(res)
