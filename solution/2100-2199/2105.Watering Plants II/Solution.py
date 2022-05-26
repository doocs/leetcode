class Solution:
    def minimumRefill(self, plants: List[int], capacityA: int, capacityB: int) -> int:
        i, j = 0, len(plants) - 1
        ans = 0
        a, b = capacityA, capacityB
        while i <= j:
            if i == j:
                if max(capacityA, capacityB) < plants[i]:
                    ans += 1
                break
            if capacityA < plants[i]:
                capacityA = a - plants[i]
                ans += 1
            else:
                capacityA -= plants[i]
            if capacityB < plants[j]:
                capacityB = b - plants[j]
                ans += 1
            else:
                capacityB -= plants[j]
            i += 1
            j -= 1
        return ans
