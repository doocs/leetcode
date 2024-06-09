class Solution:
    def numberOfChild(self, n: int, k: int) -> int:
        k, mod = divmod(k, n - 1)
        return n - mod - 1 if k & 1 else mod
