class Solution:
    def digArtifacts(
        self, n: int, artifacts: List[List[int]], dig: List[List[int]]
    ) -> int:
        def check(artifact):
            r1, c1, r2, c2 = artifact
            for x in range(r1, r2 + 1):
                for y in range(c1, c2 + 1):
                    if (x, y) not in s:
                        return False
            return True

        s = {(i, j) for i, j in dig}
        return sum(check(v) for v in artifacts)
