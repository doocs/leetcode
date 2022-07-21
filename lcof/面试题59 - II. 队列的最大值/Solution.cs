public class MaxQueue {
    LinkedList<int> mvq;
    Queue<int> q;

    public MaxQueue() {
        mvq = new LinkedList<int>();
        q = new Queue<int>();
    }
    
    public int Max_value() {
        if (mvq.Count == 0) {
            return -1;
        }
        return mvq.First.Value;
    }
    
    public void Push_back(int value) {
        q.Enqueue(value);
        while (mvq.Count > 0 && mvq.Last.Value < value) {
            mvq.RemoveLast();
        }
        mvq.AddLast(value);
    }
    
    public int Pop_front() {
        if (q.Count == 0) {
            return -1;
        }
        int v = q.Dequeue();
        if (mvq.First.Value == v) {
            mvq.RemoveFirst();
        }
        return v;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.Max_value();
 * obj.Push_back(value);
 * int param_3 = obj.Pop_front();
 */