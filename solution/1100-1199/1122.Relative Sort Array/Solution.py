class Solution:
    def relativeSortArray(self, arr1: List[int], arr2: List[int]) -> List[int]:
        mp = [0] * 1001
        for x in arr1:
            mp[x] += 1
        i = 0
        for x in arr2:
            while mp[x] > 0:
                arr1[i] = x
                mp[x] -= 1
                i += 1
        for x, cnt in enumerate(mp):
            for _ in range(cnt):
                arr1[i] = x
                i += 1
        return arr1
