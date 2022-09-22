class Solution:
    def stoneGameVI(self, aliceValues: List[int], bobValues: List[int]) -> int:
        arr = [(a + b, i) for i, (a, b) in enumerate(zip(aliceValues, bobValues))]
        arr.sort(reverse=True)
        a = sum(aliceValues[v[1]] for i, v in enumerate(arr) if i % 2 == 0)
        b = sum(bobValues[v[1]] for i, v in enumerate(arr) if i % 2 == 1)
        if a > b:
            return 1
        if a < b:
            return -1
        return 0
