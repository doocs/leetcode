class Solution:
    def destCity(self, paths: list[list[str]]) -> str:
        s = {a for a, _ in paths}
        return next(b for _, b in paths if b not in s)
