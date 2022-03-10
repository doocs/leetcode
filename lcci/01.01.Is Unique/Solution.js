/**
 * @param {string} astr
 * @return {boolean}
 */
var isUnique = function (astr) {
    let bitmap = 0;
    for (let i = 0; i < astr.length; ++i) {
        const pos = astr[i].charCodeAt() - 'a'.charCodeAt();
        if ((bitmap & (1 << pos)) != 0) {
            return false;
        }
        bitmap |= 1 << pos;
    }
    return true;
};
