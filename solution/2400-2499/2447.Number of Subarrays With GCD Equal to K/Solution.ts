function subarrayGCD(nums: number[], k: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        let x = nums[i];
        for (let j = i; j < n; j++) {
            x = gcd(nums[j], x);
            if (x == k) ans += 1;
        }
    }
    return ans;
}

function gcd(a: number, b: number): number {
    if (a > b) [a, b] = [b, a];
    if (a == 0) return b;
    return gcd(b % a, a);
}
