class Solution:
    def alertNames(self, keyName: List[str], keyTime: List[str]) -> List[str]:
        d = defaultdict(list)
        for name, t in zip(keyName, keyTime):
            t = int(t[:2]) * 60 + int(t[3:])
            d[name].append(t)
        ans = []
        for name, ts in d.items():
            if (n := len(ts)) > 2:
                ts.sort()
                for i in range(n - 2):
                    if ts[i + 2] - ts[i] <= 60:
                        ans.append(name)
                        break
        ans.sort()
        return ans
