var CQueue = function () {
    this.data = [];
    this.helper = [];
};
/**
 * @param {number} value
 * @return {void}
 */
CQueue.prototype.appendTail = function (value) {
    this.data.push(value);
};
/**
 * @return {number}
 */
CQueue.prototype.deleteHead = function () {
    if (this.data.length) {
        while (this.data.length > 1) {
            this.helper.push(this.data.pop());
        }
        let res = this.data.pop();
        while (this.helper.length) {
            this.data.push(this.helper.pop());
        }
        return res;
    } else {
        return -1;
    }
};
