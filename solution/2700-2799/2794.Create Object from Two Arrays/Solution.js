/**
 * @param {Array} keysArr
 * @param {Array} valuesArr
 * @return {Object}
 */
var createObject = function (keysArr, valuesArr) {
    const ans = {};
    for (let i = 0; i < keysArr.length; ++i) {
        const k = keysArr[i] + '';
        if (Object.prototype.hasOwnProperty.call(ans, k)) {
            continue;
        }
        set(ans, k, valuesArr[i]);
    }
    return ans;
};

function set(target, key, value) {
    Object.defineProperty(target, key, {
        value,
        writable: true,
        enumerable: true,
        configurable: true,
    });
}
