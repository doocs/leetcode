/**
 * @param {string} coordinates
 * @return {boolean}
 */
 var squareIsWhite = function(coordinates) {
    let x = coordinates.charAt(0).charCodeAt() - 'a'.charCodeAt() + 1;
    let y = Number(coordinates.charAt(1));
    return ((x + y) & 1) == 1;
};