var MyCalendar = function () {
    this.calendar = [];
};

/**
 * @param {number} start
 * @param {number} end
 * @return {boolean}
 */
MyCalendar.prototype.book = function (start, end) {
    for (const item of this.calendar) {
        if (end <= item[0] || item[1] <= start) {
            continue;
        }
        return false;
    }
    this.calendar.push([start, end]);
    return true;
};

/**
 * Your MyCalendar object will be instantiated and called as such:
 * var obj = new MyCalendar()
 * var param_1 = obj.book(start,end)
 */
