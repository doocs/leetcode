class AuctionSystem:

    def __init__(self):
        self.items = defaultdict(SortedList)
        self.users = {}

    def addBid(self, userId: int, itemId: int, bidAmount: int) -> None:
        if userId not in self.users:
            self.users[userId] = {}
        if itemId in self.users[userId]:
            self.removeBid(userId, itemId)
        self.users[userId][itemId] = bidAmount
        self.items[itemId].add((bidAmount, userId))

    def updateBid(self, userId: int, itemId: int, newAmount: int) -> None:
        oldAmount = self.users[userId][itemId]
        self.items[itemId].remove((oldAmount, userId))
        self.items[itemId].add((newAmount, userId))
        self.users[userId][itemId] = newAmount

    def removeBid(self, userId: int, itemId: int) -> None:
        oldAmount = self.users[userId][itemId]
        self.items[itemId].remove((oldAmount, userId))
        self.users[userId].pop(itemId)

    def getHighestBidder(self, itemId: int) -> int:
        ls = self.items[itemId]
        return -1 if not ls else ls[-1][1]


# Your AuctionSystem object will be instantiated and called as such:
# obj = AuctionSystem()
# obj.addBid(userId,itemId,bidAmount)
# obj.updateBid(userId,itemId,newAmount)
# obj.removeBid(userId,itemId)
# param_4 = obj.getHighestBidder(itemId)
