class Solution:
    def closetTarget(self, words: List[str], target: str, startIndex: int) -> int:
        n = len(words)
        ans = n
        for i, w in enumerate(words):
            if w == target:
                t = abs(i - startIndex)
                ans = min(ans, t, n - t)
        return -1 if ans == n else ans
