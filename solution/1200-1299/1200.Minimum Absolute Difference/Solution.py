class Solution:
    def minimumAbsDifference(self, arr: List[int]) -> List[List[int]]:
        arr.sort()
        ans = []
        mi = inf
        for a, b in pairwise(arr):
            d = b - a
            if d < mi:
                ans = [(a, b)]
                mi = d
            elif d == mi:
                ans.append((a, b))
        return ans
