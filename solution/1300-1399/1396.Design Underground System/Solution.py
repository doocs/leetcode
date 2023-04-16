class UndergroundSystem:
    def __init__(self):
        self.ts = {}
        self.d = {}

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.ts[id] = (t, stationName)

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        t0, station = self.ts[id]
        x = self.d.get((station, stationName), (0, 0))
        self.d[(station, stationName)] = (x[0] + t - t0, x[1] + 1)

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        x = self.d[(startStation, endStation)]
        return x[0] / x[1]


# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)
