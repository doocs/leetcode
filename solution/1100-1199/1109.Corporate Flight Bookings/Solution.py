class Solution:
    def corpFlightBookings(self, bookings: List[List[int]], n: int) -> List[int]:
        delta = [0] * n
        for first, last, seats in bookings:
            delta[first - 1] += seats
            if last < n:
                delta[last] -= seats
        return list(accumulate(delta))
