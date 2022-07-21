class Solution:
    def smallestTrimmedNumbers(
        self, nums: List[str], queries: List[List[int]]
    ) -> List[int]:
        ans = []
        for k, t in queries:
            x = nums[:]
            for i, v in enumerate(x):
                x[i] = v[-t:]
            p = list(zip(x, range(len(x))))
            p.sort(key=lambda v: (int(v[0]), v[1]))
            ans.append(p[k - 1][1])
        return ans
