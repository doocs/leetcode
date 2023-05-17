/**
 * @param {string} s
 * @return {string}
 */
var sortString = function (s) {
    let rs = '';
    const m = new Map();
    for (let i = 0; i < s.length; i++) {
        m.set(s[i], (m.get(s[i]) || 0) + 1);
    }
    const keys = [...m.keys()];
    keys.sort();
    while (rs.length < s.length) {
        for (let j = 0; j < keys.length; j++) {
            if (m.get(keys[j]) != 0) {
                rs += keys[j];
                m.set(keys[j], m.get(keys[j]) - 1);
            }
        }
        for (let j = keys.length - 1; j >= 0; j--) {
            if (m.get(keys[j]) != 0) {
                rs += keys[j];
                m.set(keys[j], m.get(keys[j]) - 1);
            }
        }
    }
    return rs;
};
