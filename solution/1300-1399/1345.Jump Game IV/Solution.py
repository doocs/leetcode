class Solution:
    def minJumps(self, arr: List[int]) -> int:
        idx = defaultdict(list)
        for i, v in enumerate(arr):
            idx[v].append(i)
        q = deque([(0, 0)])
        vis = {0}
        while q:
            i, step = q.popleft()
            if i == len(arr) - 1:
                return step
            v = arr[i]
            step += 1
            for j in idx[v]:
                if j not in vis:
                    vis.add(j)
                    q.append((j, step))
            del idx[v]
            if i + 1 < len(arr) and (i + 1) not in vis:
                vis.add(i + 1)
                q.append((i + 1, step))
            if i - 1 >= 0 and (i - 1) not in vis:
                vis.add(i - 1)
                q.append((i - 1, step))
