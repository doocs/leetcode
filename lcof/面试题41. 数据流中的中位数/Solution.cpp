class MedianFinder {
public:
    /** initialize your data structure here. */
    MedianFinder() {
    }

    void addNum(int num) {
        if (q1.size() > q2.size()) {
            q1.push(num);
            q2.push(q1.top());
            q1.pop();
        } else {
            q2.push(num);
            q1.push(q2.top());
            q2.pop();
        }
    }

    double findMedian() {
        if (q1.size() > q2.size()) {
            return q1.top();
        }
        return (q1.top() + q2.top()) / 2.0;
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