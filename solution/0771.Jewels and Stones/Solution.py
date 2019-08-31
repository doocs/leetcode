class Solution:
    def numJewelsInStones(self, J: str, S: str) -> int:
        record = {ch for ch in J}
        sum = 0
        for ch in S:
            sum += 1 if ch in record else 0
        return sum