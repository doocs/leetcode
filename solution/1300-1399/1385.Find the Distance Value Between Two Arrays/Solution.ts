function findTheDistanceValue(arr1: number[], arr2: number[], d: number): number {
    const check = (a: number) => {
        let l = 0;
        let r = arr2.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (arr2[mid] >= a - d) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l === arr2.length || arr2[l] > a + d;
    };
    arr2.sort((a, b) => a - b);
    let ans = 0;
    for (const a of arr1) {
        if (check(a)) {
            ++ans;
        }
    }
    return ans;
}
