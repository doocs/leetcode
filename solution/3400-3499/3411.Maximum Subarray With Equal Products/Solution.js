var maxLength = function (nums) {
    const gcd = (a, b) => (b ? gcd(b, a % b) : a);
    const lcm = (a, b) => (a / gcd(a, b)) * b;
    let mx = 0,
        ml = 1;
    for (const x of nums) {
        mx = Math.max(mx, x);
        ml = lcm(ml, x);
    }
    const maxP = ml * mx;
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let p = 1,
            g = 0,
            l = 1;
        for (let j = i; j < n; ++j) {
            p *= nums[j];
            g = gcd(g, nums[j]);
            l = lcm(l, nums[j]);
            if (p === g * l) {
                ans = Math.max(ans, j - i + 1);
            }
            if (p > maxP) {
                break;
            }
        }
    }
    return ans;
};
