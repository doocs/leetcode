function countRectangles(rectangles: number[][], points: number[][]): number[] {
    const n = 101;
    let ymap = Array.from({ length: n }, v => []);
    for (let [x, y] of rectangles) {
        ymap[y].push(x);
    }
    for (let nums of ymap) {
        nums.sort((a, b) => a - b);
    }
    let ans = [];
    for (let [x, y] of points) {
        let count = 0;
        for (let h = y; h < n; h++) {
            const nums = ymap[h];
            let left = 0,
                right = nums.length;
            while (left < right) {
                let mid = (left + right) >> 1;
                if (x > nums[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            count += nums.length - right;
        }
        ans.push(count);
    }
    return ans;
}
