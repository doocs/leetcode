class Solution:
    def digitCount(self, num: str) -> bool:
        cnt = Counter(num)
        return all(int(v) == cnt[str(i)] for i, v in enumerate(num))
