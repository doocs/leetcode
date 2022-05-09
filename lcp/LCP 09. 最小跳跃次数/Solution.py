class Solution:
    def minJump(self, jump: List[int]) -> int:
        n = len(jump)
        vis = [False] * (n + 1)
        q = deque([0])
        ans = 0
        vis[0] = True
        mx = 1
        while q:
            for _ in range(len(q)):
                i = q.popleft()
                if i + jump[i] >= n:
                    return ans + 1
                for j in list(range(mx, i)) + [i + jump[i]]:
                    if not vis[j]:
                        q.append(j)
                        vis[j] = True
                mx = max(mx, i + 1)
            ans += 1
        return -1
