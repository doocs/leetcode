public class SeatManager {
    private PriorityQueue<int, int> q = new PriorityQueue<int, int>();

    public SeatManager(int n) {
        for (int i = 1; i <= n; ++i) {
            q.Enqueue(i, i);
        }
    }

    public int Reserve() {
        return q.Dequeue();
    }

    public void Unreserve(int seatNumber) {
        q.Enqueue(seatNumber, seatNumber);
    }
}

/**
 * Your SeatManager object will be instantiated and called as such:
 * SeatManager obj = new SeatManager(n);
 * int param_1 = obj.Reserve();
 * obj.Unreserve(seatNumber);
 */
