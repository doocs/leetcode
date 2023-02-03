class Solution:
    def minNumber(self, nums: List[int]) -> str:
        def cmp(a, b):
            x, y = a + b, b + a
            return -1 if x < y else 1

        ans = [str(x) for x in nums]
        ans.sort(key=cmp_to_key(cmp))
        return "".join(ans)
