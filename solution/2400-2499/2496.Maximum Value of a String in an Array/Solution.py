class Solution:
    def maximumValue(self, strs: List[str]) -> int:
        def f(s):
            return int(s) if all(c.isdigit() for c in s) else len(s)

        return max(f(s) for s in strs)
