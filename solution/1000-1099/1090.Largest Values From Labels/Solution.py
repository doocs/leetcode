class Solution:
    def largestValsFromLabels(self, values: List[int], labels: List[int], numWanted: int, useLimit: int) -> int:
        n = len(values)
        idx = list(range(n))
        idx.sort(key=lambda i: -values[i])
        ans = num = 0
        counter = Counter()
        for i in idx:
            v, l = values[i], labels[i]
            if counter[l] < useLimit:
                counter[l] += 1
                ans += v
                num += 1
            if num == numWanted:
                break
        return ans
