class Solution:
    def canFormArray(self, arr: List[int], pieces: List[List[int]]) -> bool:
        mapper = {piece[0]: piece for piece in pieces}
        i, n = 0, len(arr)
        while i < n:
            if arr[i] not in mapper:
                return False
            vals = mapper[arr[i]]
            for val in vals:
                if arr[i] != val:
                    return False
                i += 1
        return True
