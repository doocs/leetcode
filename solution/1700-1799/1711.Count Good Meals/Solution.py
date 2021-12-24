class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 1000000007
        limit = max(deliciousness) * 2
        pairs = 0
        freq = defaultdict(int)
        for d in deliciousness:
            target = 1
            while target <= limit:
                pairs = (pairs + freq[target - d]) % mod
                target = target << 1
            freq[d] += 1
        return pairs
