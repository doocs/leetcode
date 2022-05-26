class Solution:
    def decodeCiphertext(self, encodedText: str, rows: int) -> str:
        ans = []
        cols = len(encodedText) // rows
        for j in range(cols):
            x, y = 0, j
            while x < rows and y < cols:
                ans.append(encodedText[x * cols + y])
                x, y = x + 1, y + 1
        return ''.join(ans).rstrip()
