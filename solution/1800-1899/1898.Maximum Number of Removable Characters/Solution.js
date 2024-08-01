/**
 * @param {string} s
 * @param {string} p
 * @param {number[]} removable
 * @return {number}
 */
function maximumRemovals(s, p, removable) {
    const str_len = s.length;
    const sub_len = p.length;

    /**
     * @param {number} k
     * @return {boolean}
     */
    function isSub(k) {
        const removed = new Set(removable.slice(0, k));

        let sub_i = 0;
        for (let str_i = 0; str_i < str_len; ++str_i) {
            if (s.charAt(str_i) === p.charAt(sub_i) && !removed.has(str_i)) {
                ++sub_i;
                if (sub_i >= sub_len) {
                    break;
                }
            }
        }
        return sub_i === sub_len;
    }

    let left = 0;
    let right = removable.length;

    while (left < right) {
        const middle = (left + right) >> 1;
        if (isSub(middle + 1)) {
            left = middle + 1;
        } else {
            right = middle;
        }
    }
    return left;
}
