class Solution:
    def averageHeightOfBuildings(self, buildings: List[List[int]]) -> List[List[int]]:
        height = defaultdict(int)
        cnt = defaultdict(int)
        for s, e, h in buildings:
            cnt[s] += 1
            cnt[e] -= 1
            height[s] += h
            height[e] -= h
        ans = []
        i = h = n = 0
        for j in sorted(cnt.keys()):
            if n:
                t = [i, j, h // n]
                if ans and ans[-1][1] == i and ans[-1][2] == t[-1]:
                    ans[-1][1] = j
                else:
                    ans.append(t)
            i = j
            h += height[j]
            n += cnt[j]
        return ans
