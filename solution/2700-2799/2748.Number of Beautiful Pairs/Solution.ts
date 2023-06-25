function countBeautifulPairs(nums: number[]): number {
    const cnt: number[] = Array(10).fill(0);
    let ans = 0;
    for (let x of nums) {
        for (let y = 0; y < 10; ++y) {
            if (cnt[y] > 0 && gcd(x % 10, y) === 1) {
                ans += cnt[y];
            }
        }
        while (x > 9) {
            x = Math.floor(x / 10);
        }
        ++cnt[x];
    }
    return ans;
}

function gcd(a: number, b: number): number {
    if (b === 0) {
        return a;
    }
    return gcd(b, a % b);
}
