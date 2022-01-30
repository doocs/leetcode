class Solution:
    def uncommonFromSentences(self, s1: str, s2: str) -> List[str]:
        c = Counter(s1.split()) + Counter(s2.split())
        return [w for w, n in c.items() if n == 1]
