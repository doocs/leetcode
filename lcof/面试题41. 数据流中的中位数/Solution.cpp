class MedianFinder {
public:
    /** initialize your data structure here. */
    MedianFinder() {
    }

    void addNum(int num) {
        if (maxHeap.size() == minHeap.size()) {
            maxHeap.push(num);
            int temp = maxHeap.top();
            maxHeap.pop();
            minHeap.push(temp);
        } else {
            minHeap.push(num);
            int temp = minHeap.top();
            minHeap.pop();
            maxHeap.push(temp);
        }
    }

    double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.top() + minHeap.top()) / 2.0;
        }
        return minHeap.top();
    }

private:
    priority_queue<int> maxHeap;
    priority_queue<int, vector<int>, greater<int>> minHeap;
};
