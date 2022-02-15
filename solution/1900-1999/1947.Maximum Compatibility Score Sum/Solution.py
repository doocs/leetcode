class Solution:
    def maxCompatibilitySum(
        self, students: List[List[int]], mentors: List[List[int]]
    ) -> int:
        def score(s, m):
            res = 0
            for i in range(len(s)):
                res += 1 if s[i] == m[i] else 0
            return res

        m, n = len(students), len(students[0])
        scores = [[0] * m for _ in range(m)]
        for i in range(m):
            for j in range(m):
                scores[i][j] = score(students[i], mentors[j])
        p = self.permute(list(range(m)))
        mx = 0
        for item in p:
            t = 0
            sidx = 0
            for midx in item:
                t += scores[sidx][midx]
                sidx += 1
            mx = max(mx, t)
        return mx

    def permute(self, nums):
        def dfs(nums, i, res, path, used):
            if i == len(nums):
                res.append(copy.deepcopy(path))
                return
            for j in range(len(nums)):
                if not used[j]:
                    path.append(nums[j])
                    used[j] = True
                    dfs(nums, i + 1, res, path, used)
                    used[j] = False
                    path.pop()

        res, path = [], []
        used = [False] * len(nums)
        dfs(nums, 0, res, path, used)
        return res
