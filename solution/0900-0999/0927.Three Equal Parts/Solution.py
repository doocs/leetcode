class Solution:
    def threeEqualParts(self, arr: List[int]) -> List[int]:
        def find(cnt):
            s = 0
            for i, v in enumerate(arr):
                s += v
                if s == cnt:
                    return i
            return -1

        n = len(arr)
        cnt, mod = divmod(sum(arr), 3)
        if mod:
            return [-1, -1]
        if cnt == 0:
            return [0, n - 1]
        i = find(1)
        j = find(cnt + 1)
        k = find(cnt * 2 + 1)
        while k < n and arr[i] == arr[j] == arr[k]:
            i, j, k = i + 1, j + 1, k + 1
        if k == n:
            return [i - 1, j]
        return [-1, -1]
