/**
 * @param {string} num
 * @param {number[]} change
 * @return {string}
 */
var maximumNumber = function (num, change) {
    const s = num.split('');
    let changed = false;
    for (let i = 0; i < s.length; ++i) {
        const d = change[+s[i]].toString();
        if (changed && d < s[i]) {
            break;
        }
        if (d > s[i]) {
            s[i] = d;
            changed = true;
        }
    }
    return s.join('');
};
