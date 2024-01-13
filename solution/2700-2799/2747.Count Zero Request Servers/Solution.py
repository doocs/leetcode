class Solution:
    def countServers(
        self, n: int, logs: List[List[int]], x: int, queries: List[int]
    ) -> List[int]:
        cnt = Counter()
        logs.sort(key=lambda x: x[1])
        ans = [0] * len(queries)
        j = k = 0
        for r, i in sorted(zip(queries, count())):
            l = r - x
            while k < len(logs) and logs[k][1] <= r:
                cnt[logs[k][0]] += 1
                k += 1
            while j < len(logs) and logs[j][1] < l:
                cnt[logs[j][0]] -= 1
                if cnt[logs[j][0]] == 0:
                    cnt.pop(logs[j][0])
                j += 1
            ans[i] = n - len(cnt)
        return ans
