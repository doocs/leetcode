class Solution:
    def minStickers(self, stickers: List[str], target: str) -> int:
        q = deque([0])
        ans = 0
        n = len(target)
        vis = [False] * (1 << n)
        vis[0] = True
        while q:
            for _ in range(len(q)):
                state = q.popleft()
                if state == (1 << n) - 1:
                    return ans
                for s in stickers:
                    nxt = state
                    cnt = Counter(s)
                    for i, c in enumerate(target):
                        if not (nxt & (1 << i)) and cnt[c]:
                            nxt |= 1 << i
                            cnt[c] -= 1
                    if not vis[nxt]:
                        vis[nxt] = True
                        q.append(nxt)
            ans += 1
        return -1
