class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        map = defaultdict(int)
        for i, num in enumerate(arr):
            map[num] = i
        for i, num in enumerate(arr):
            if num << 1 in map and i != map[num << 1]:
                return True
        return False
