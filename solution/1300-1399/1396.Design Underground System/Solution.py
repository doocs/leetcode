class UndergroundSystem:
    def __init__(self):
        self.check_in_station = {}
        self.check_in_time = {}
        self.total_time = {}

    def checkIn(self, id: int, stationName: str, t: int) -> None:
        self.check_in_station[id] = stationName
        self.check_in_time[id] = t

    def checkOut(self, id: int, stationName: str, t: int) -> None:
        cost = t - self.check_in_time.pop(id)
        start_station = self.check_in_station.pop(id)
        stations = start_station + '.' + stationName
        times = self.total_time.get(stations, [0, 0])
        times[0] += cost
        times[1] += 1
        self.total_time[stations] = times

    def getAverageTime(self, startStation: str, endStation: str) -> float:
        stations = startStation + '.' + endStation
        times = self.total_time[stations]
        return times[0] / times[1]


# Your UndergroundSystem object will be instantiated and called as such:
# obj = UndergroundSystem()
# obj.checkIn(id,stationName,t)
# obj.checkOut(id,stationName,t)
# param_3 = obj.getAverageTime(startStation,endStation)
