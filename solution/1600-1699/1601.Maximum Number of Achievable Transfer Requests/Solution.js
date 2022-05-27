/**
 * @param {number} n
 * @param {number[][]} requests
 * @return {number}
 */
var maximumRequests = function (n, requests) {
    function check(x) {
        let d = new Array(n).fill(0);
        for (let i = 0; i < m; ++i) {
            if ((x >> i) & 1) {
                const [f, t] = requests[i];
                d[f]--;
                d[t]++;
            }
        }
        for (const v of d) {
            if (v) {
                return false;
            }
        }
        return true;
    }
    let ans = 0;
    let m = requests.length;
    for (let mask = 1; mask < 1 << m; ++mask) {
        let cnt = mask.toString(2).split('0').join('').length;
        if (ans < cnt && check(mask)) {
            ans = cnt;
        }
    }
    return ans;
};
