class Solution:
    def largestTimeFromDigits(self, arr: List[int]) -> str:
        ans = -1
        for i in range(4):
            for j in range(4):
                for k in range(4):
                    if i != j and i != k and j != k:
                        h = arr[i] * 10 + arr[j]
                        m = arr[k] * 10 + arr[6 - i - j - k]
                        if h < 24 and m < 60:
                            ans = max(ans, h * 60 + m)
        return '' if ans < 0 else f'{ans // 60:02}:{ans % 60:02}'
