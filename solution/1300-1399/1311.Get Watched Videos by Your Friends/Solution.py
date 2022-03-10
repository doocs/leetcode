class Solution:
    def watchedVideosByFriends(
        self,
        watchedVideos: List[List[str]],
        friends: List[List[int]],
        id: int,
        level: int,
    ) -> List[str]:
        n = len(friends)
        vis = [False] * n
        q = deque([id])
        vis[id] = True
        for _ in range(level):
            size = len(q)
            for _ in range(size):
                u = q.popleft()
                for v in friends[u]:
                    if not vis[v]:
                        q.append(v)
                        vis[v] = True
        freq = Counter()
        for _ in range(len(q)):
            u = q.pop()
            for w in watchedVideos[u]:
                freq[w] += 1
        videos = list(freq.items())
        videos.sort(key=lambda x: (x[1], x[0]))
        return [v[0] for v in videos]
