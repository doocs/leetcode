/**
 * initialize your data structure here.
 */
var MinStack = function () {
    this.stack = [];
    this.minStack = [];
};

/**
 * @param {number} x
 * @return {void}
 */
MinStack.prototype.push = function (x) {
    this.stack.unshift(x);
    if (!this.minStack.length || this.minStack[0] >= x) {
        this.minStack.unshift(x);
    }
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {
    if (this.stack.shift() === this.minStack[0]) {
        this.minStack.shift();
    }
};

/**
 * @return {number}
 */
MinStack.prototype.top = function () {
    return this.stack[0];
};

/**
 * @return {number}
 */
MinStack.prototype.min = function () {
    return this.minStack[0];
};

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(x)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.min()
 */
