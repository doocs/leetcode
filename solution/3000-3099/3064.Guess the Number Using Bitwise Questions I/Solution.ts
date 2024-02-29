/**
 * Definition of commonSetBits API.
 * var commonSetBits = function(num: number): number {}
 */

function findNumber(): number {
    let n = 0;
    for (let i = 0; i < 32; ++i) {
        if (commonSetBits(1 << i)) {
            n |= 1 << i;
        }
    }
    return n;
}
