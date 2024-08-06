class Solution:
    def averageHeightOfBuildings(self, buildings: List[List[int]]) -> List[List[int]]:
        cnt = defaultdict(int)
        d = defaultdict(int)
        for start, end, height in buildings:
            cnt[start] += 1
            cnt[end] -= 1
            d[start] += height
            d[end] -= height
        s = m = 0
        last = -1
        ans = []
        for k, v in sorted(d.items()):
            if m:
                avg = s // m
                if ans and ans[-1][2] == avg and ans[-1][1] == last:
                    ans[-1][1] = k
                else:
                    ans.append([last, k, avg])
            s += v
            m += cnt[k]
            last = k
        return ans
