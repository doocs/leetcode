/**
 * @param {string} num1
 * @param {string} num2
 * @return {string}
 */
var addStrings = function (num1, num2) {
    let i = num1.length - 1;
    let j = num2.length - 1;
    const ans = [];
    for (let c = 0; i >= 0 || j >= 0 || c; --i, --j) {
        c += i < 0 ? 0 : parseInt(num1.charAt(i), 10);
        c += j < 0 ? 0 : parseInt(num2.charAt(j), 10);
        ans.push(c % 10);
        c = Math.floor(c / 10);
    }
    return ans.reverse().join('');
};

/**
 * @param {string} num1
 * @param {string} num2
 * @return {string}
 */
var subStrings = function (num1, num2) {
    const m = num1.length;
    const n = num2.length;
    const neg = m < n || (m == n && num1 < num2);
    if (neg) {
        const t = num1;
        num1 = num2;
        num2 = t;
    }
    let i = num1.length - 1;
    let j = num2.length - 1;
    const ans = [];
    for (let c = 0; i >= 0; --i, --j) {
        c = parseInt(num1.charAt(i), 10) - c;
        if (j >= 0) {
            c -= parseInt(num2.charAt(j), 10);
        }
        ans.push((c + 10) % 10);
        c = c < 0 ? 1 : 0;
    }
    while (ans.length > 1 && ans[ans.length - 1] == '0') {
        ans.pop();
    }
    if (neg) {
        ans.push('-');
    }
    return ans.reverse().join('');
};
