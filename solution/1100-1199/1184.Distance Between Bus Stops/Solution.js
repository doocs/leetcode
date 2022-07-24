/**
 * @param {number[]} distance
 * @param {number} start
 * @param {number} destination
 * @return {number}
 */
var distanceBetweenBusStops = function (distance, start, destination) {
    if (start > destination) {
        return distanceBetweenBusStops(distance, destination, start);
    }
    let a = 0;
    let b = 0;
    for (let i = 0; i < distance.length; ++i) {
        if (i >= start && i < destination) {
            a += distance[i];
        } else {
            b += distance[i];
        }
    }
    return Math.min(a, b);
};
