/**
 * @param {number} n
 * @param {number} k
 * @return {string}
 */
var getHappyString = function (n, k) {
    if (k > 3 * (1 << (n - 1))) {
        return '';
    }
    const cs = 'abc';
    const ans = [];
    for (let i = 0; i < n; i++) {
        const remain = 1 << (n - i - 1);
        for (let j = 0; j < cs.length; j++) {
            const c = cs[j];
            if (ans.at(-1) === c) {
                continue;
            }
            if (k <= remain) {
                ans.push(c);
                break;
            }
            k -= remain;
        }
    }
    return ans.join('');
};
