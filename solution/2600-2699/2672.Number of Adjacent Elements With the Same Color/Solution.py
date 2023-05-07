class Solution:
    def colorTheArray(self, n: int, queries: List[List[int]]) -> List[int]:
        nums = [0] * n
        ans = [0] * len(queries)
        x = 0
        for k, (i, c) in enumerate(queries):
            if i > 0 and nums[i] and nums[i - 1] == nums[i]:
                x -= 1
            if i < n - 1 and nums[i] and nums[i + 1] == nums[i]:
                x -= 1
            if i > 0 and nums[i - 1] == c:
                x += 1
            if i < n - 1 and nums[i + 1] == c:
                x += 1
            ans[k] = x
            nums[i] = c
        return ans
