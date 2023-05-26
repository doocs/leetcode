/**
 * // This is the MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Master {
 *      get(index: number): number {}
 *
 *      length(): number {}
 * }
 */

function findInMountainArray(target: number, mountainArr: MountainArray) {
    const n = mountainArr.length();
    let l = 0;
    let r = n - 1;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (mountainArr.get(mid) > mountainArr.get(mid + 1)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    const search = (l: number, r: number, k: number): number => {
        while (l < r) {
            const mid = (l + r) >> 1;
            if (k * mountainArr.get(mid) >= k * target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return mountainArr.get(l) === target ? l : -1;
    };
    const ans = search(0, l, 1);
    return ans === -1 ? search(l + 1, n - 1, -1) : ans;
}
