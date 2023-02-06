class MedianFinder {
public:
    /** initialize your data structure here. */
    MedianFinder() {
    }

    void addNum(int num) {
        q1.push(num);
        q2.push(q1.top());
        q1.pop();
        if (q2.size() - q1.size() > 1) {
            q1.push(q2.top());
            q2.pop();
        }
    }

    double findMedian() {
        if (q2.size() > q1.size()) {
            return q2.top();
        }
        return (double) (q1.top() + q2.top()) / 2;
    }

private:
    priority_queue<int, vector<int>, greater<int>> q1;
    priority_queue<int> q2;
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder* obj = new MedianFinder();
 * obj->addNum(num);
 * double param_2 = obj->findMedian();
 */