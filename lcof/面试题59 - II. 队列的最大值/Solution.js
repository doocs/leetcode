var MaxQueue = function () {
    this.q1 = [];
    this.q2 = [];
};

/**
 * @return {number}
 */
MaxQueue.prototype.max_value = function () {
    return this.q2.length ? this.q2[0] : -1;
};

/**
 * @param {number} value
 * @return {void}
 */
MaxQueue.prototype.push_back = function (value) {
    while (this.q2.length && this.q2[this.q2.length - 1] < value) {
        this.q2.pop();
    }
    this.q1.push(value);
    this.q2.push(value);
};

/**
 * @return {number}
 */
MaxQueue.prototype.pop_front = function () {
    if (!this.q1.length) {
        return -1;
    }
    const ans = this.q1.shift();
    if (this.q2[0] == ans) {
        this.q2.shift();
    }
    return ans;
};

/**
 * Your MaxQueue object will be instantiated and called as such:
 * var obj = new MaxQueue()
 * var param_1 = obj.max_value()
 * obj.push_back(value)
 * var param_3 = obj.pop_front()
 */
