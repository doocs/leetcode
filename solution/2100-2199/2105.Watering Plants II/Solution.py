class Solution:
    def minimumRefill(self, plants: List[int], capacityA: int, capacityB: int) -> int:
        a, b = capacityA, capacityB
        ans = 0
        i, j = 0, len(plants) - 1
        while i < j:
            if a < plants[i]:
                ans += 1
                a = capacityA
            a -= plants[i]
            if b < plants[j]:
                ans += 1
                b = capacityB
            b -= plants[j]
            i, j = i + 1, j - 1
        ans += i == j and max(a, b) < plants[i]
        return ans
