class Solution:
    def matchReplacement(self, s: str, sub: str, mappings: List[List[str]]) -> bool:
        d = [[False] * 128 for _ in range(128)]
        for a, b in mappings:
            d[ord(a)][ord(b)] = True
        for i in range(len(s) - len(sub) + 1):
            if all(
                a == b or d[ord(b)][ord(a)] for a, b in zip(s[i : i + len(sub)], sub)
            ):
                return True
        return False
