function maximumTripletValue(nums: number[]): number {
    let [ans, mx, mxDiff] = [0, 0, 0];
    for (const x of nums) {
        ans = Math.max(ans, mxDiff * x);
        mxDiff = Math.max(mxDiff, mx - x);
        mx = Math.max(mx, x);
    }
    return ans;
}
