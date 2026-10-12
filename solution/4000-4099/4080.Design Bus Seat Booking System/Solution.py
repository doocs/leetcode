class BusBooking:
    def __init__(self, n: int):
        self.tot = 0
        self.min_tot = 0
        self.cost_data = [[0] * 4 for _ in range(n)]

    def toggle(self, seat: str) -> None:
        i = int(seat[1:]) - 1
        j = ord(seat[0]) - ord("A")
        if self.cost_data[i][j] > 0:
            self.min_tot -= 1
            self.tot -= self.cost_data[i][j]
            self.cost_data[i][j] = 0
            return
        cost = 1
        if (j == 0 and self.cost_data[i][1] > 0) or (
            j == 3 and self.cost_data[i][2] > 0
        ):
            cost = 3
        self.min_tot += 1
        self.tot += cost
        self.cost_data[i][j] = cost

    def getTotalTime(self) -> int:
        return self.tot

    def getMinTime(self) -> int:
        return self.min_tot


# Your BusBooking object will be instantiated and called as such:
# obj = BusBooking(n)
# obj.toggle(seat)
# param_2 = obj.getTotalTime()
# param_3 = obj.getMinTime()
