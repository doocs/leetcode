var MyCalendarTwo = function () {
    this.events = [];
    this.overlaps = [];
};

/**
 * @param {number} start
 * @param {number} end
 * @return {boolean}
 */
MyCalendarTwo.prototype.book = function (start, end) {
    for (let [s, e] of this.overlaps) {
        if (Math.max(start, s) < Math.min(end, e)) {
            return false;
        }
    }

    for (let [s, e] of this.events) {
        if (Math.max(start, s) < Math.min(end, e)) {
            this.overlaps.push([Math.max(start, s), Math.min(end, e)]);
        }
    }

    this.events.push([start, end]);
    return true;
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(start,end)
 */
