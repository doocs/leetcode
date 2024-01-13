/**
 * // This is the CustomFunction's API interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *      f(x: number, y: number): number {}
 * }
 */

function findSolution(customfunction: CustomFunction, z: number): number[][] {
    const ans: number[][] = [];
    for (let x = 1; x <= 1000; ++x) {
        let l = 1;
        let r = 1000;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (customfunction.f(x, mid) >= z) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (customfunction.f(x, l) == z) {
            ans.push([x, l]);
        }
    }
    return ans;
}
