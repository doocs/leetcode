class Solution:
    def largestTimeFromDigits(self, arr: List[int]) -> str:
        cnt = [0] * 10
        for v in arr:
            cnt[v] += 1
        for h in range(23, -1, -1):
            for m in range(59, -1, -1):
                t = [0] * 10
                t[h // 10] += 1
                t[h % 10] += 1
                t[m // 10] += 1
                t[m % 10] += 1
                if cnt == t:
                    return f'{h:02}:{m:02}'
        return ''
