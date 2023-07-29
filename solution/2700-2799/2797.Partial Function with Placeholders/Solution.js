/**
 * @param {Function} fn
 * @param {Array} args
 * @return {Function}
 */
var partial = function (fn, args) {
    return function (...restArgs) {
        let i = 0;
        for (let j = 0; j < args.length; ++j) {
            if (args[j] === '_') {
                args[j] = restArgs[i++];
            }
        }
        while (i < restArgs.length) {
            args.push(restArgs[i++]);
        }
        return fn.apply(this, args);
    };
};
