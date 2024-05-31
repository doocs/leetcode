class Solution:
    def hasGroupsSizeX(self, deck: List[int]) -> bool:
        cnt = Counter(deck)
        return reduce(gcd, cnt.values()) >= 2
