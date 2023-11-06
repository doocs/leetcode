public class SeatManager {
    private SortedSet<int> availableSeats;

    public SeatManager(int n) {
        availableSeats = new SortedSet<int>();
        for (int i = 1; i <= n; i++) {
            availableSeats.Add(i);
        }
    }
    
    public int Reserve() {
        int reservedSeat = availableSeats.Min;
        availableSeats.Remove(reservedSeat);
        return reservedSeat;
    }
    
    public void Unreserve(int seatNumber) {
        availableSeats.Add(seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.Reserve();
 * obj.Unreserve(seatNumber);
 */
