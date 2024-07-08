/**
 * @param {number[]} arr
 * @param {number} m
 * @return {number}
 */
const findLatestStep = function (arr, m) {
    let result = -1;
    const len = arr.length;
    const reOnes = new RegExp(`\\b1{${m}}\\b`);
    arr.reduce( (accum, item, iIndex) => { 
        accum[item-1] = '1';
        iIndex++;
        if (iIndex >= m && reOnes.test(accum.join(''))) { 
            result = iIndex;
        }
        return accum
    },  [...(" ".repeat(len))] );
    return result
}
