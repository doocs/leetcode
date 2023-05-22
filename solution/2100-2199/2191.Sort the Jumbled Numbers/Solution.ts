function sortJumbled(mapping: number[], nums: number[]): number[] {
    const n = nums.length;
    const arr: number[][] = [];
    for (let i = 0; i < n; ++i) {
        let x = nums[i];
        let y = x === 0 ? mapping[0] : 0;
        let k = 1;
        for (; x > 0; x = Math.floor(x / 10), k *= 10) {
            y += mapping[x % 10] * k;
        }
        arr.push([y, i]);
    }
    arr.sort((a, b) => (a[0] === b[0] ? a[1] - b[1] : a[0] - b[0]));
    return arr.map(a => nums[a[1]]);
}
