/**
 * @param {string} coordinates
 * @return {boolean}
 */
var squareIsWhite = function (coordinates) {
    return (coordinates[0].charCodeAt() + coordinates[1].charCodeAt()) % 2 == 1;
};
