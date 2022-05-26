var FrontMiddleBackQueue = function () {
    this.left = [];
    this.right = [];
};

/**
 * @param {number} val
 * @return {void}
 */
FrontMiddleBackQueue.prototype.pushFront = function (val) {
    this.left.unshift(val);
    this.rebalance();
};

/**
 * @param {number} val
 * @return {void}
 */
FrontMiddleBackQueue.prototype.pushMiddle = function (val) {
    this.left.push(val);
    this.rebalance();
};

/**
 * @param {number} val
 * @return {void}
 */
FrontMiddleBackQueue.prototype.pushBack = function (val) {
    this.right.push(val);
    this.rebalance();
};

/**
 * @return {number}
 */
FrontMiddleBackQueue.prototype.popFront = function () {
    if (this.isEmpty()) return -1;
    let num = this.left.length == 0 ? this.right.shift() : this.left.shift();
    this.rebalance();
    return num;
};

/**
 * @return {number}
 */
FrontMiddleBackQueue.prototype.popMiddle = function () {
    if (this.isEmpty()) return -1;
    let num =
        this.left.length == this.right.length
            ? this.left.pop()
            : this.right.shift();
    this.rebalance();
    return num;
};

/**
 * @return {number}
 */
FrontMiddleBackQueue.prototype.popBack = function () {
    if (this.isEmpty()) return -1;
    let num = this.right.pop();
    this.rebalance();
    return num;
};

FrontMiddleBackQueue.prototype.rebalance = function () {
    while (this.left.length > this.right.length) {
        this.right.unshift(this.left.pop());
    }
    while (this.right.length > this.left.length + 1) {
        this.left.push(this.right.shift());
    }
};

FrontMiddleBackQueue.prototype.isEmpty = function () {
    return this.left.length == 0 && this.right.length == 0;
};

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * var obj = new FrontMiddleBackQueue()
 * obj.pushFront(val)
 * obj.pushMiddle(val)
 * obj.pushBack(val)
 * var param_4 = obj.popFront()
 * var param_5 = obj.popMiddle()
 * var param_6 = obj.popBack()
 */
