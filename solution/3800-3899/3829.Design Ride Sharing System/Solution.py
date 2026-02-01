class RideSharingSystem:

    def __init__(self):
        self.t = 0
        self.riders = SortedList()
        self.drivers = SortedList()
        self.d = defaultdict(int)

    def addRider(self, riderId: int) -> None:
        self.d[riderId] = self.t
        self.riders.add((self.t, riderId))
        self.t += 1

    def addDriver(self, driverId: int) -> None:
        self.drivers.add((self.t, driverId))
        self.t += 1

    def matchDriverWithRider(self) -> List[int]:
        if len(self.riders) < 1 or len(self.drivers) < 1:
            return [-1, -1]
        return [self.drivers.pop(0)[1], self.riders.pop(0)[1]]

    def cancelRider(self, riderId: int) -> None:
        self.riders.discard((self.d[riderId], riderId))


# Your RideSharingSystem object will be instantiated and called as such:
# obj = RideSharingSystem()
# obj.addRider(riderId)
# obj.addDriver(driverId)
# param_3 = obj.matchDriverWithRider()
# obj.cancelRider(riderId)
