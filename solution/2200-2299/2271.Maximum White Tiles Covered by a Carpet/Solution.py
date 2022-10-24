class Solution:
    def maximumWhiteTiles(self, tiles: List[List[int]], carpetLen: int) -> int:
        tiles.sort()
        n = len(tiles)
        s = ans = j = 0
        for i, (li, ri) in enumerate(tiles):
            while j < n and tiles[j][1] - li + 1 <= carpetLen:
                s += tiles[j][1] - tiles[j][0] + 1
                j += 1
            if j < n and li + carpetLen > tiles[j][0]:
                ans = max(ans, s + li + carpetLen - tiles[j][0])
            else:
                ans = max(ans, s)
            s -= ri - li + 1
        return ans
