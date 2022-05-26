class Solution:
    def canMakeArithmeticProgression(self, arr: List[int]) -> bool:
        arr.sort()
        for i in range(1, len(arr) - 1):
            if (arr[i] << 1) != arr[i - 1] + arr[i + 1]:
                return False
        return True
