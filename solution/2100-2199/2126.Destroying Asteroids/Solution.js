/**
 * @param {number} mass
 * @param {number[]} asteroids
 * @return {boolean}
 */
var asteroidsDestroyed = function (mass, asteroids) {
    asteroids.sort((a, b) => a - b);
    for (const x of asteroids) {
        if (mass < x) {
            return false;
        }
        mass += x;
    }
    return true;
};
