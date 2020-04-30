/**
 * @param {number} n
 * @return {number}
 */
var fib = function(n) {
    if(!n) return 0
    let pre = 0
    let cur = 1
    for(let i=2;i<=n;i++) {
        let c = (pre + cur)%(1e9+7)
        pre = cur
        cur = c
    }
    return cur
};