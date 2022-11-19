/**
 * @param {string} time
 * @return {string}
 */
var maximumTime = function (time) {
    const t = Array.from(time);
    if (t[0] === '?') {
        t[0] = t[1] >= '4' && t[1] <= '9' ? '1' : '2';
    }
    if (t[1] === '?') {
        t[1] = t[0] == '2' ? '3' : '9';
    }
    if (t[3] === '?') {
        t[3] = '5';
    }
    if (t[4] === '?') {
        t[4] = '9';
    }
    return t.join('');
};
