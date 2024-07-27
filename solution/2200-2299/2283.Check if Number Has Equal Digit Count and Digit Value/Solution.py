class Solution:
    def digitCount(self, num: str) -> bool:
        cnt = Counter(int(x) for x in num)
        return all(cnt[i] == int(x) for i, x in enumerate(num))
