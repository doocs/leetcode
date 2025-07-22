class Solution:
    def reverseStr(self, s: str, k: int) -> str:
        cs = list(s)
        for i in range(0, len(cs), 2 * k):
            cs[i : i + k] = reversed(cs[i : i + k])
        return "".join(cs)
