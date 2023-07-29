function fullBloomFlowers(flowers: number[][], people: number[]): number[] {
    const n = flowers.length;
    const start = new Array(n).fill(0);
    const end = new Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        start[i] = flowers[i][0];
        end[i] = flowers[i][1];
    }
    start.sort((a, b) => a - b);
    end.sort((a, b) => a - b);
    const ans: number[] = [];
    for (const p of people) {
        const r = search(start, p + 1);
        const l = search(end, p);
        ans.push(r - l);
    }
    return ans;
}

function search(nums: number[], x: number): number {
    let l = 0;
    let r = nums.length;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l;
}
