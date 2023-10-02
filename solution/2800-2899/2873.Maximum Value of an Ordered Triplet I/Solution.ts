function maximumTripletValue(nums: number[]): number {
    let [ans, mx, mx_diff] = [0, 0, 0];
    for (const num of nums) {
        ans = Math.max(ans, mx_diff * num);
        mx = Math.max(mx, num);
        mx_diff = Math.max(mx_diff, mx - num);
    }
    return ans;
}
