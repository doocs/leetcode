function minWastedSpace(packages: number[], boxes: number[][]): number {
    const n = packages.length;
    const inf = Infinity;
    packages.sort((a, b) => a - b);
    let ans = inf;
    for (const box of boxes) {
        box.sort((a, b) => a - b);
        if (packages[n - 1] > box[box.length - 1]) {
            continue;
        }
        let s = 0;
        let i = 0;
        for (const b of box) {
            const j = search(packages, b, i);
            s += (j - i) * b;
            i = j;
        }
        ans = Math.min(ans, s);
    }
    if (ans === inf) {
        return -1;
    }
    const s = packages.reduce((a, b) => a + b, 0);
    return (ans - s) % 1000000007;
}

function search(nums: number[], x: number, l: number): number {
    let r = nums.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] > x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
