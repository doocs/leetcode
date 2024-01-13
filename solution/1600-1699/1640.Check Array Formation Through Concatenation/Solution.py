class Solution:
    def canFormArray(self, arr: List[int], pieces: List[List[int]]) -> bool:
        i = 0
        while i < len(arr):
            k = 0
            while k < len(pieces) and pieces[k][0] != arr[i]:
                k += 1
            if k == len(pieces):
                return False
            j = 0
            while j < len(pieces[k]) and arr[i] == pieces[k][j]:
                i, j = i + 1, j + 1
        return True
