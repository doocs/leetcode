/**
 * @param {Object|Array} obj
 * @return {Object|Array}
 */
var compactObject = function (obj) {
    if (!obj || typeof obj !== 'object') {
        return obj;
    }
    if (Array.isArray(obj)) {
        return obj.map(compactObject).filter(Boolean);
    }
    return Object.entries(obj).reduce((acc, [key, value]) => {
        const compactedValue = compactObject(value);
        if (compactedValue) {
            acc[key] = compactedValue;
        }
        return acc;
    }, {});
};
