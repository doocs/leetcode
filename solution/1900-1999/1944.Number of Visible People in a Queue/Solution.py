class Solution:
    def canSeePersonsCount(self, heights: List[int]) -> List[int]:
        n = len(heights)
        ans = [0] * n
        stack = list()

        for i in range(n - 1, -1, -1):
            while stack:
                ans[i] += 1
                if heights[i] > stack[-1]:
                    stack.pop()
                else:
                    break
            stack.append(heights[i])

        return ans
