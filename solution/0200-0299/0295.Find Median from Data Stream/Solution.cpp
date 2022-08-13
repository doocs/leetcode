class MedianFinder {
public:
    /** initialize your data structure here. */
    MedianFinder() { }

    void addNum(int num) {
        max_heap.push(num);
        min_heap.push(max_heap.top());
        max_heap.pop();
        if (min_heap.size() > max_heap.size()) {
            max_heap.push(min_heap.top());
            min_heap.pop();
        }
    }

    double findMedian() {
        if (max_heap.size() > min_heap.size()) {
            return max_heap.top();
        }
        return (double)(max_heap.top() + min_heap.top()) / 2;
    }

private:
    priority_queue<int> max_heap;
    priority_queue<int, vector<int>, greater<int>> min_heap;
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder* obj = new MedianFinder();
 * obj->addNum(num);
 * double param_2 = obj->findMedian();
 */
