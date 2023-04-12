function findLucky(arr: number[]): number {
    const cnt = new Array(510).fill(0);
    for (const x of arr) {
        ++cnt[x];
    }
    let ans = -1;
    for (let x = 1; x < cnt.length; ++x) {
        if (cnt[x] === x) {
            ans = x;
        }
    }
    return ans;
}
