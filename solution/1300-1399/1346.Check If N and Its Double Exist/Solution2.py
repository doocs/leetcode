class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        s = set()
        for v in arr:
            if v * 2 in s or (v % 2 == 0 and v // 2 in s):
                return True
            s.add(v)
        return False
