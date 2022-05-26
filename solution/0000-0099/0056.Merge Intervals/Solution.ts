function merge(intervals: number[][]): number[][] {
    intervals.sort((a, b) => a[0] - b[0]);
    let ans: number[][] = [];
    let index: number = -1;
    for (let interval of intervals) {
        if (index == -1 || ans[index][1] < interval[0]) {
            // 保留
            ans.push(interval);
            index++;
        } else {
            // 求交集
            ans[index][1] = Math.max(ans[index][1], interval[1]);
        }
    }
    return ans;
}
