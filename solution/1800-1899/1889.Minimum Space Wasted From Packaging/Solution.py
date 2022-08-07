class Solution:
    def minWastedSpace(self, packages: List[int], boxes: List[List[int]]) -> int:
        packages.sort()
        res = inf
        for box in boxes:
            box.sort()
            if packages[-1] > box[-1]:
                continue
            t = last = 0
            for b in box:
                idx = bisect_right(packages, b, lo=last)
                t += (idx - last) * b
                last = idx
            res = min(res, t)
        return -1 if res == inf else (res - sum(packages)) % (10**9 + 7)
