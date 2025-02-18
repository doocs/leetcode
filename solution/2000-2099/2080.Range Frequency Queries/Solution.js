/**
 * @param {number[]} arr
 */
var RangeFreqQuery = function (arr) {
    this.g = new Map();

    for (let i = 0; i < arr.length; ++i) {
        if (!this.g.has(arr[i])) {
            this.g.set(arr[i], []);
        }
        this.g.get(arr[i]).push(i);
    }
};

/**
 * @param {number} left
 * @param {number} right
 * @param {number} value
 * @return {number}
 */
RangeFreqQuery.prototype.query = function (left, right, value) {
    const idx = this.g.get(value);
    if (!idx) {
        return 0;
    }
    const l = _.sortedIndex(idx, left);
    const r = _.sortedIndex(idx, right + 1);
    return r - l;
};

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * var obj = new RangeFreqQuery(arr)
 * var param_1 = obj.query(left,right,value)
 */
