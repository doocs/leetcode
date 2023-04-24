var MinStack = function () {
    this.stk1 = [];
    this.stk2 = [Infinity];
};

/**
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function (val) {
    this.stk1.push(val);
    this.stk2.push(Math.min(this.stk2[this.stk2.length - 1], val));
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function () {
    this.stk1.pop();
    this.stk2.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function () {
    return this.stk1[this.stk1.length - 1];
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function () {
    return this.stk2[this.stk2.length - 1];
};

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
