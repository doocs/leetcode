/**
 * @param {number[]} derived
 * @return {boolean}
 */
var doesValidArrayExist = function (derived) {
    return derived.reduce((acc, x) => acc ^ x) === 0;
};
