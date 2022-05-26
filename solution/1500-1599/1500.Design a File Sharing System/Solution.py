class FileSharing:
    def __init__(self, m: int):
        self.cur = 0
        self.chunks = m
        self.reused = []
        self.user_chunks = defaultdict(set)

    def join(self, ownedChunks: List[int]) -> int:
        if self.reused:
            userID = heappop(self.reused)
        else:
            self.cur += 1
            userID = self.cur
        self.user_chunks[userID] = set(ownedChunks)
        return userID

    def leave(self, userID: int) -> None:
        heappush(self.reused, userID)
        self.user_chunks.pop(userID)

    def request(self, userID: int, chunkID: int) -> List[int]:
        if chunkID < 1 or chunkID > self.chunks:
            return []
        res = []
        for k, v in self.user_chunks.items():
            if chunkID in v:
                res.append(k)
        if res:
            self.user_chunks[userID].add(chunkID)
        return sorted(res)


# Your FileSharing object will be instantiated and called as such:
# obj = FileSharing(m)
# param_1 = obj.join(ownedChunks)
# obj.leave(userID)
# param_3 = obj.request(userID,chunkID)
