/**
 * @param {number} num
 * @return {number}
 */
function findComplement(num) {
    return ~num & (2 ** num.toString(2).length - 1);
}
