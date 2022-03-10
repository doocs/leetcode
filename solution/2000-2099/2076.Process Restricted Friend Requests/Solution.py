class Solution:
    def friendRequests(
        self, n: int, restrictions: List[List[int]], requests: List[List[int]]
    ) -> List[bool]:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        ans = []
        i = 0
        for u, v in requests:
            if find(u) == find(v):
                ans.append(True)
            else:
                valid = True
                for x, y in restrictions:
                    if (find(u) == find(x) and find(v) == find(y)) or (
                        find(u) == find(y) and find(v) == find(x)
                    ):
                        valid = False
                        break
                ans.append(valid)
                if valid:
                    p[find(u)] = find(v)
        return ans
