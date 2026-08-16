class Solution:
    def maximumGap(self, skill: str, station: str) -> int:
        n, m = len(skill), len(station)
        suf = [0] * n
        j = m - 1
        for i in range(n - 1, 0, -1):
            while station[j] != skill[i]:
                j -= 1
            suf[i] = j
            j -= 1

        ans = pre = 0
        for i in range(n - 1):
            while station[pre] != skill[i]:
                pre += 1
            ans = max(ans, suf[i + 1] - pre)
            pre += 1
        return ans
