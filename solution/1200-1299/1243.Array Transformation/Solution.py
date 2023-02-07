class Solution:
    def transformArray(self, arr: List[int]) -> List[int]:
        f = True
        while f:
            f = False
            t = arr[:]
            for i in range(1, len(t) - 1):
                if t[i] > t[i - 1] and t[i] > t[i + 1]:
                    arr[i] -= 1
                    f = True
                if t[i] < t[i - 1] and t[i] < t[i + 1]:
                    arr[i] += 1
                    f = True
        return arr
