/**
 * @param {string} s
 * @return {number}
 */
 var minSwaps = function(s) {
    let n = s.length;
    let n1 = [...s].reduce((a, c) => parseInt(c) + a, 0);
    let n0 = n - n1;
    let count = Infinity;
    let half = n / 2;
    // 101、1010
    if (n1 == Math.ceil(half) && n0 == Math.floor(half)) {
        let cur = 0;
        for (let i = 0; i < n; i++) {
            if (i % 2 == 0 && s.charAt(i) != '1') cur++;
        }
        count = Math.min(count, cur);
    }
    // 010、0101
    if (n0 == Math.ceil(half) && n1 == Math.floor(half)) {
        let cur = 0;
        for (let i = 0; i < n; i++) {
            if (i % 2 == 0 && s.charAt(i) != '0') cur++;
        }
        count = Math.min(count, cur);
    }
    return count == Infinity ? -1 : count;
};