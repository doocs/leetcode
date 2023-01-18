/**
 * @param {string} date
 * @return {number}
 */
var dayOfYear = function (date) {
    const y = +date.slice(0, 4);
    const m = +date.slice(5, 7);
    const d = +date.slice(8);
    const v = y % 400 == 0 || (y % 4 == 0 && y % 100) ? 29 : 28;
    const days = [31, v, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days.slice(0, m - 1).reduce((a, b) => a + b, d);
};
