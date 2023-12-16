class Solution:
    def subarrayBitwiseORs(self, arr: List[int]) -> int:
        s = {0}
        ans = set()
        for x in arr:
            s = {x | y for y in s} | {x}
            ans |= s
        return len(ans)
