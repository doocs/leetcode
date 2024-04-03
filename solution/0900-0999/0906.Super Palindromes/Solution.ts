const ps = Array(2e5).fill(0);

const init = (() => {
    for (let i = 1; i <= 1e5; ++i) {
        const s: string = i.toString();
        const t1: string = s.split('').reverse().join('');
        const t2: string = s.slice(0, -1).split('').reverse().join('');
        ps[2 * i - 2] = parseInt(s + t1, 10);
        ps[2 * i - 1] = parseInt(s + t2, 10);
    }
})();

function superpalindromesInRange(left: string, right: string): number {
    const l = BigInt(left);
    const r = BigInt(right);
    const isPalindrome = (x: bigint): boolean => {
        const s: string = x.toString();
        return s === s.split('').reverse().join('');
    };
    let ans = 0;
    for (const p of ps) {
        const x = BigInt(p) * BigInt(p);
        if (x >= l && x <= r && isPalindrome(x)) {
            ++ans;
        }
    }
    return ans;
}
