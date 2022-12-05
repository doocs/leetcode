/**
 * @param {string} coordinates
 * @return {boolean}
 */
var squareIsWhite = function (coordinates) {
    const x = coordinates.charAt(0).charCodeAt() - 'a'.charCodeAt() + 1;
    const y = Number(coordinates.charAt(1));
    return ((x + y) & 1) == 1;
};
