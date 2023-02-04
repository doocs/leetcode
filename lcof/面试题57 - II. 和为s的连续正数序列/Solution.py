class Solution:
    def findContinuousSequence(self, target: int) -> List[List[int]]:
        l, r = 1, 2
        ans = []
        while l < r:
            s = (l + r) * (r - l + 1) // 2
            if s == target:
                ans.append(list(range(l, r + 1)))
                l += 1
            elif s < target:
                r += 1
            else:
                l += 1
        return ans
