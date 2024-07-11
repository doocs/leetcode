class Solution:
    def subarrayBitwiseORs(self, arr: List[int]) -> int:
        ans = set()
        s = set()
        for x in arr:
            s = {x | y for y in s} | {x}
            ans |= s
        return len(ans)
