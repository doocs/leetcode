class Solution:
    def minJumps(self, arr: List[int]) -> int:
        g = defaultdict(list)
        for i, x in enumerate(arr):
            g[x].append(i)
        q = deque([0])
        vis = {0}
        ans = 0
        while 1:
            for _ in range(len(q)):
                i = q.popleft()
                if i == len(arr) - 1:
                    return ans
                for j in (i + 1, i - 1, *g.pop(arr[i], [])):
                    if 0 <= j < len(arr) and j not in vis:
                        q.append(j)
                        vis.add(j)
            ans += 1
