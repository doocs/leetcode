class Solution:
    def supplyWagon(self, supplies: List[int]) -> List[int]:
        for _ in range((len(supplies) + 1) >> 1):
            n = len(supplies)
            mi = inf
            k = 0
            for i in range(n - 1):
                x = supplies[i] + supplies[i + 1]
                if mi > x:
                    mi = x
                    k = i
            t = []
            i = 0
            while i < n:
                if i == k:
                    t.append(mi)
                    i += 2
                else:
                    t.append(supplies[i])
                    i += 1
            supplies = t
        return supplies
