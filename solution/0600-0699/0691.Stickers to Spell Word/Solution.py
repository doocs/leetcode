class Solution:
    def minStickers(self, stickers: List[str], target: str) -> int:
        n = len(target)
        q = deque([0])
        vis = [False] * (1 << n)
        vis[0] = True
        ans = 0
        while q:
            for _ in range(len(q)):
                cur = q.popleft()
                if cur == (1 << n) - 1:
                    return ans
                for s in stickers:
                    cnt = Counter(s)
                    nxt = cur
                    for i, c in enumerate(target):
                        if (cur >> i & 1) == 0 and cnt[c] > 0:
                            cnt[c] -= 1
                            nxt |= 1 << i
                    if not vis[nxt]:
                        vis[nxt] = True
                        q.append(nxt)
            ans += 1
        return -1
