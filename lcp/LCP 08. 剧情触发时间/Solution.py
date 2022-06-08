class Solution:
    def getTriggerTime(self, increase: List[List[int]], requirements: List[List[int]]) -> List[int]:
        increase.insert(0, [0,0,0])
        for i in range(1, len(increase)):
            for j in range(3):
                increase[i][j] += increase[i-1][j]

        res = [None for _ in range(len(requirements))]
        maxs = increase[-1]
        for i in range(len(requirements)):
            # value in requirements beyond the limit
            if any(a > b for a, b in zip(requirements[i], maxs)):
                res[i] = -1
                continue
            # else
            left, right = 0, len(increase) - 1
            while left <= right:
                mid = (left + right) // 2
                if all(a >= b for a, b in zip(increase[mid], requirements[i])):
                    right = mid - 1
                    res[i] = mid
                else:
                    left = mid + 1
            
        return res
