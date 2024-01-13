class Solution:
    def runeReserve(self, runes: List[int]) -> int:
        runes.sort()
        ans = i = 0
        for j, x in enumerate(runes):
            if j and runes[j] - runes[j - 1] > 1:
                i = j
            else:
                ans = max(ans, j - i + 1)
        return ans
