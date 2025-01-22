/**
 * @param {string[]} operations
 * @return {number}
 */
var finalValueAfterOperations = function (operations) {
    return operations.reduce((acc, op) => acc + (op[1] === '+' ? 1 : -1), 0);
};
