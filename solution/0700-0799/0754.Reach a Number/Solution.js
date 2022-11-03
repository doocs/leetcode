/**
 * @param {number} target
 * @return {number}
 */
var reachNumber = function (target) {
    target = Math.abs(target);
    let [s, k] = [0, 0];
    while (1) {
        if (s >= target && (s - target) % 2 == 0) {
            return k;
        }
        ++k;
        s += k;
    }
};
