/**
 * @param {string} coordinates
 * @return {boolean}
 */
var squareIsWhite = function (coordinates) {
    const x = coordinates.charAt(0).charCodeAt();
    const y = coordinates.charAt(1).charCodeAt();
    return (x + y) % 2 == 1;
};
