class Solution:
    def minimizeError(self, prices: List[str], target: int) -> str:
        mi = 0
        arr = []
        for p in prices:
            p = float(p)
            mi += int(p)
            if d := p - int(p):
                arr.append(d)
        if not mi <= target <= mi + len(arr):
            return "-1"
        d = target - mi
        arr.sort(reverse=True)
        ans = d - sum(arr[:d]) + sum(arr[d:])
        return f'{ans:.3f}'
