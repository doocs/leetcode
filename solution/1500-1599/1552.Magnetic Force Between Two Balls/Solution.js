/**
 * @param {number[]} position
 * @param {number} m
 * @return {number}
 */
var maxDistance = function (position, m) {
    position.sort((a, b) => a - b);
    let [l, r] = [1, position.at(-1)];
    const count = f => {
        let cnt = 1;
        let prev = position[0];
        for (const curr of position) {
            if (curr - prev >= f) {
                cnt++;
                prev = curr;
            }
        }
        return cnt;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (count(mid) >= m) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
};
