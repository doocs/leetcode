class Solution:
    def countElements(self, arr: List[int]) -> int:
        s = set(arr)
        res = 0
        for num in arr:
            if num + 1 in s:
                res += 1
        return res
