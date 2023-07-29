/**
 * @param {Array<Function>} functions
 * @return {Promise}
 */
var promiseAllSettled = function (functions) {
    return new Promise(resolve => {
        const res = [];
        let count = 0;
        for (let i in functions) {
            functions[i]()
                .then(value => ({ status: 'fulfilled', value }))
                .catch(reason => ({ status: 'rejected', reason }))
                .then(obj => {
                    res[i] = obj;
                    if (++count === functions.length) {
                        resolve(res);
                    }
                });
        }
    });
};
