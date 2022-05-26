/**
 * @param {number} target
 * @return {number[][]}
 */
var findContinuousSequence = function (target) {
    let res = [];
    let window = [];
    let i = 1;
    let sum = 0;
    while (1) {
        if (sum < target) {
            window.push(i);
            sum += i;
            i++;
        } else if (sum > target) {
            let a = window.shift();
            if (window.length < 2) break;
            sum -= a;
        } else {
            res.push([...window]);
            window.push(i);
            sum += i;
            i++;
            if (window.length === 2) break;
        }
    }
    return res;
};
