/**
 * @param {string} astr
 * @return {boolean}
 */
var isUnique = function (astr) {
    let mask = 0;
    for (const c of astr) {
        const i = c.charCodeAt() - 'a'.charCodeAt();
        if ((mask >> i) & 1) {
            return false;
        }
        mask |= 1 << i;
    }
    return true;
};
