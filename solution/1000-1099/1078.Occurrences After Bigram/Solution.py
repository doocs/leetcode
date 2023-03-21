class Solution:
    def findOcurrences(self, text: str, first: str, second: str) -> List[str]:
        ws = text.split()
        n = len(ws)
        return [
            ws[i + 2] for i in range(n - 2) if ws[i] == first and ws[i + 1] == second
        ]
