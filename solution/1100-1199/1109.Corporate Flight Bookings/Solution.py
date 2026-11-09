class Solution:
    def corpFlightBookings(self, bookings: list[list[int]], n: int) -> list[int]:
        ans = [0] * n
        for first, last, seats in bookings:
            ans[first - 1] += seats
            if last < n:
                ans[last] -= seats
        return list(accumulate(ans))
