class Solution:
    def pathExistenceQueries(
        self, n: int, nums: List[int], maxDiff: int, queries: List[List[int]]
    ) -> List[int]:
        pairs = sorted((x, i) for i, x in enumerate(nums))
        m = 20
        f = [[0] * m for _ in range(n)]
        r = n - 1
        for l in range(n - 1, -1, -1):
            while pairs[r][0] - pairs[l][0] > maxDiff:
                r -= 1
            i, j = pairs[l][1], pairs[r][1]
            f[i][0] = j
            for k in range(1, m):
                f[i][k] = f[f[i][k - 1]][k - 1]

        ans = []
        for i, j in queries:
            if nums[i] > nums[j]:
                i, j = j, i
            if i == j:
                ans.append(0)
                continue
            if nums[i] == nums[j]:
                ans.append(1)
                continue
            d = 0
            for k in range(m - 1, -1, -1):
                if nums[f[i][k]] < nums[j]:
                    d |= 1 << k
                    i = f[i][k]
            if nums[f[i][0]] < nums[j]:
                ans.append(-1)
            else:
                ans.append(d + 1)
        return ans
