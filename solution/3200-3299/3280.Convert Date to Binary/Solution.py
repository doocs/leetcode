class Solution:
    def convertDateToBinary(self, date: str) -> str:
        return "-".join(f"{int(s):b}" for s in date.split("-"))
