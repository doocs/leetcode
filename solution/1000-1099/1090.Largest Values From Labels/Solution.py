class Solution:
    def largestValsFromLabels(
        self, values: list[int], labels: list[int], numWanted: int, useLimit: int
    ) -> int:
        ans = num = 0
        cnt = Counter()
        for v, l in sorted(zip(values, labels), reverse=True):
            if cnt[l] < useLimit:
                cnt[l] += 1
                num += 1
                ans += v
                if num == numWanted:
                    break
        return ans
