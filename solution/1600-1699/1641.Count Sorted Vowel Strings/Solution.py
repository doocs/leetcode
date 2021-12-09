class Solution:
    def countVowelStrings(self, n: int) -> int:
        cnt = [1] * 5
        for i in range(2, n + 1):
            for j in range(3, -1, -1):
                cnt[j] += cnt[j + 1]
        return sum(cnt)
