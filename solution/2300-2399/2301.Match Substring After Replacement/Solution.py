class Solution:
    def matchReplacement(self, s: str, sub: str, mappings: List[List[str]]) -> bool:
        d = defaultdict(set)
        for a, b in mappings:
            d[a].add(b)
        for i in range(len(s) - len(sub) + 1):
            if all(a == b or a in d[b] for a, b in zip(s[i : i + len(sub)], sub)):
                return True
        return False
