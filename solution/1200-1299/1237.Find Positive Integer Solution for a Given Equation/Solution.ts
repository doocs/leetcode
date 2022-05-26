/**
 * // This is the CustomFunction's API interface.
 * // You should not implement it, or speculate about its implementation
 * class CustomFunction {
 *      f(x: number, y: number): number {}
 * }
 */

function findSolution(customfunction: CustomFunction, z: number): number[][] {
    // 二分
    let ans = [];
    for (let i = 1; i <= 1000; i++) {
        let left = 1,
            right = 1000;
        while (left < right) {
            let mid = (left + right) >> 1;
            if (customfunction.f(i, mid) >= z) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (customfunction.f(i, left) == z) {
            ans.push([i, left]);
        }
    }
    return ans;
}
