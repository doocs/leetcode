class Solution:
    def maximumValue(self, strs: list[str]) -> int:
        def f(s: str) -> int:
            return int(s) if all(c.isdigit() for c in s) else len(s)

        return max(f(s) for s in strs)
