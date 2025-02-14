class Solution:
    def longestWord(self, words: List[str]) -> str:
        def dfs(w: str) -> bool:
            if not w:
                return True
            for k in range(1, len(w) + 1):
                if w[:k] in s and dfs(w[k:]):
                    return True
            return False

        s = set(words)
        words.sort(key=lambda x: (-len(x), x))
        for w in words:
            s.remove(w)
            if dfs(w):
                return w
        return ""
