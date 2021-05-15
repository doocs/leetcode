/**
 * initialize your data structure here.
 */
 var MinStack = function() {
    this.s = [];
    this.mins = [Infinity];
};

/** 
 * @param {number} val
 * @return {void}
 */
MinStack.prototype.push = function(val) {
    this.s.push(val);
    this.mins.push(Math.min(this.mins[this.mins.length - 1], val));
};

/**
 * @return {void}
 */
MinStack.prototype.pop = function() {
    this.s.pop();
    this.mins.pop();
};

/**
 * @return {number}
 */
MinStack.prototype.top = function() {
    return this.s[this.s.length - 1];
};

/**
 * @return {number}
 */
MinStack.prototype.getMin = function() {
    return this.mins[this.mins.length - 1];
};

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */