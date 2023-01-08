class Solution:
    def categorizeBox(self, length: int, width: int, height: int, mass: int) -> str:
        v = length * width * height
        bulky = int(any(x >= 10000 for x in (length, width, height)) or v >= 10**9)
        heavy = int(mass >= 100)
        i = heavy << 1 | bulky
        d = ['Neither', 'Bulky', 'Heavy', 'Both']
        return d[i]
