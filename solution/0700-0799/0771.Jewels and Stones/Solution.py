class Solution:
    def numJewelsInStones(self, jewels: str, stones: str) -> int:
        jewel_set = {c for c in jewels}
        return sum([1 for c in stones if c in jewel_set])
