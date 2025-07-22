class Solution:
    def checkTwoChessboards(self, coordinate1: str, coordinate2: str) -> bool:
        x = ord(coordinate1[0]) - ord(coordinate2[0])
        y = int(coordinate1[1]) - int(coordinate2[1])
        return (x + y) % 2 == 0
