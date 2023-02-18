/**
 * // This is the CustomFunction's API interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *      f(x: number, y: number): number {}
 * }
 */

function findSolution(customfunction: CustomFunction, z: number): number[][] {
    let x = 1;
    let y = z;
    const ans: number[][] = [];
    while (x <= z && y) {
        const t = customfunction.f(x, y);
        if (t < z) {
            ++x;
        } else if (t > z) {
            --y;
        } else {
            ans.push([x--, y--]);
        }
    }
    return ans;
}
