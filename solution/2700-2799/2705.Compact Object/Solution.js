/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function (obj) {
    if (!obj || typeof obj !== 'object') {
        return obj;
    }
    if (Array.isArray(obj)) {
        return obj.filter(Boolean).map(compactObject);
    }
    return Object.entries(obj).reduce((acc, [key, value]) => {
        if (value) {
            set(acc, key, compactObject(value));
        }
        return acc;
    }, {});
};

function set(target, key, value) {
    Object.defineProperty(target, key, {
        value,
        writable: true,
        enumerable: true,
        configurable: true,
    });
}
