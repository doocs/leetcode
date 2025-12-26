var MedianFinder = function () {
    this.minQ = new MinPriorityQueue();
    this.maxQ = new MaxPriorityQueue();
};

/**
 * @param {number} num
 * @return {void}
 */
MedianFinder.prototype.addNum = function (num) {
    this.maxQ.enqueue(num);
    this.minQ.enqueue(this.maxQ.dequeue());
    if (this.minQ.size() - this.maxQ.size() > 1) {
        this.maxQ.enqueue(this.minQ.dequeue());
    }
};

/**
 * @return {number}
 */
MedianFinder.prototype.findMedian = function () {
    if (this.minQ.size() === this.maxQ.size()) {
        return (this.minQ.front() + this.maxQ.front()) / 2;
    }
    return this.minQ.front();
};

/**
 * Your MedianFinder object will be instantiated and called as such:
 * var obj = new MedianFinder()
 * obj.addNum(num)
 * var param_2 = obj.findMedian()
 */
