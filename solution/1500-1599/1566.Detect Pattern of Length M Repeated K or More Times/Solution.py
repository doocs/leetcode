class Solution:
    def containsPattern(self, arr: List[int], m: int, k: int) -> bool:
        if len(arr) < m * k:
            return False
        cnt, target = 0, (k - 1) * m
        for i in range(m, len(arr)):
            if arr[i] == arr[i - m]:
                cnt += 1
                if cnt == target:
                    return True
            else:
                cnt = 0
        return False
