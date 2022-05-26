class Solution:
    def countConsistentStrings(self, allowed: str, words: List[str]) -> int:
        res = 0
        chars = set(allowed)
        for word in words:
            find = True
            for c in word:
                if c not in chars:
                    find = False
                    break
            if find:
                res += 1
        return res
