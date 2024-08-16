/**
 * @param {number[][]} arrays
 * @return {number}
 */
var maxDistance = function(arrays) {
    let minVal = arrays[0][0];
    let maxVal = arrays[0][arrays[0].length - 1];
    let maxDist = 0;

    for (let i = 1; i < arrays.length; i++) {
        const currentMin = arrays[i][0];
        const currentMax = arrays[i][arrays[i].length - 1];
        maxDist = Math.max(maxDist, Math.abs(currentMax - minVal), Math.abs(maxVal - currentMin));
        minVal = Math.min(minVal, currentMin);
        maxVal = Math.max(maxVal, currentMax);
    }

    return maxDist;
};
