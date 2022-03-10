var CQueue = function () {
    this.stk1 = [];
    this.stk2 = [];
};

/**
 * @param {number} value
 * @return {void}
 */
CQueue.prototype.appendTail = function (value) {
    this.stk1.push(value);
};

/**
 * @return {number}
 */
CQueue.prototype.deleteHead = function () {
    if (!this.stk2.length) {
        while (this.stk1.length) {
            this.stk2.push(this.stk1.pop());
        }
    }
    return this.stk2.length ? this.stk2.pop() : -1;
};

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = new CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */
