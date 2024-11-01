class Solution:
    def maxEnergyBoost(self, energyDrinkA: List[int], energyDrinkB: List[int]) -> int:
        f, g = energyDrinkA[0], energyDrinkB[0]
        for a, b in zip(energyDrinkA[1:], energyDrinkB[1:]):
            f, g = max(f + a, g), max(g + b, f)
        return max(f, g)
