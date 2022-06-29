/**
 * @param {number[]} position
 * @param {number} m
 * @return {number}
 */
var maxDistance = function (position, m) {
    position.sort((a, b) => {
        return a - b;
    });
    let left = 1,
        right = position[position.length - 1];
    const check = function (f) {
        let prev = position[0];
        let cnt = 1;
        for (let i = 1; i < position.length; ++i) {
            const curr = position[i];
            if (curr - prev >= f) {
                prev = curr;
                ++cnt;
            }
        }
        return cnt >= m;
    };
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
};
