/**
 * @param {number[][]} arrays
 * @return {number}
 */
var maxDistance = arrays =>
    arrays.reduce(
        ([res, min, max], a) => [
            Math.max(Math.max(a.at(-1) - min, max - a[0]), res),
            Math.min(min, a[0]),
            Math.max(max, a.at(-1)),
        ],
        [0, Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY],
    )[0];
