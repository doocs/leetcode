class Solution:
    def rearrangeBarcodes(self, barcodes: list[int]) -> list[int]:
        cnt = Counter(barcodes)
        barcodes.sort(key=lambda x: (-cnt[x], x))
        n = len(barcodes)
        ans = [0] * len(barcodes)
        ans[::2] = barcodes[: (n + 1) // 2]
        ans[1::2] = barcodes[(n + 1) // 2 :]
        return ans
