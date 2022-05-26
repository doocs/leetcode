/**
 * @param {string} date
 * @return {number}
 */
var dayOfYear = function (date) {
    const year = +date.slice(0, 4);
    const month = +date.slice(5, 7);
    const day = +date.slice(8);
    const d =
        year % 400 === 0 || (year % 4 === 0 && year % 100 !== 0) ? 29 : 28;
    const days = [31, d, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    let ans = day;
    for (let i = 0; i < month - 1; ++i) {
        ans += days[i];
    }
    return ans;
};
