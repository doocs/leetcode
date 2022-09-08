function constructArray(n: number, k: number): number[] {
    let l = 1;
    let r = n;
    const ans = new Array(n);
    for (let i = 0; i < k; ++i) {
        ans[i] = i % 2 == 0 ? l++ : r--;
    }
    for (let i = k; i < n; ++i) {
        ans[i] = k % 2 == 0 ? r-- : l++;
    }
    return ans;
}
