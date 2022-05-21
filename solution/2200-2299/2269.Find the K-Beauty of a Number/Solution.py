class Solution:
    def divisorSubstrings(self, num: int, k: int) -> int:
        cnt = 0
        s = str(num)
        for i in range(len(s) - k + 1):
            tmp = int(s[i: i + k])
            if tmp != 0 and num % tmp == 0:
                cnt += 1
        return cnt