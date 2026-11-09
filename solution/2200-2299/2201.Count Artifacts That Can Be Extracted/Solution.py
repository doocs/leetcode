class Solution:
    def digArtifacts(
        self, n: int, artifacts: list[list[int]], dig: list[list[int]]
    ) -> int:
        def check(a: list[int]) -> bool:
            x1, y1, x2, y2 = a
            return all(
                (x, y) in s for x in range(x1, x2 + 1) for y in range(y1, y2 + 1)
            )

        s = {(i, j) for i, j in dig}
        return sum(check(a) for a in artifacts)
