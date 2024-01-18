class Solution:
    def longestCommomSubsequence(self, arrays: List[List[int]]) -> List[int]:
        n = len(arrays)
        counter = defaultdict(int)
        for array in arrays:
            for e in array:
                counter[e] += 1
        return [e for e, count in counter.items() if count == n]
