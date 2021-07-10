/**
 * @param {number} n
 * @return {number}
 */
 var tribonacci = function(n) {
    let a = 0;
    let b = 1;
    let c = 1;
    while (n--) {
        let d = a + b + c;
        a = b;
        b = c;
        c = d;
    }
    return a;
};