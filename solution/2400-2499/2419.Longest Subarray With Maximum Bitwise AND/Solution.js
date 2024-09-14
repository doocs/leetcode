function longestSubarray(nums) {
    const mx = Math.max(...nums);
    let [ans, cnt] = [0, 0];

    for (const x of nums) {
        if (x === mx) {
            cnt++;
            ans = Math.max(ans, cnt);
        } else {
            cnt = 0;
        }
    }

    return ans;
}
