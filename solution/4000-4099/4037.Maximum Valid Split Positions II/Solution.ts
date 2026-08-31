function maxValidSplits(nums: number[]): number {
    const n = nums.length;

    const pos1 = mark(nums);

    const rev = [...nums].reverse();
    const pos2 = mark(rev);

    let ans = calc(nums);

    for (let i = 0; i < n; ++i) {
        if (pos1[i] || pos2[n - 1 - i]) {
            const arr = nums.slice(0, i).concat(nums.slice(i + 1));
            ans = Math.max(ans, calc(arr));
        }
    }

    return ans;
}

function mark(nums: number[]): boolean[] {
    const n = nums.length;
    const pos = Array(n).fill(false);

    pos[0] = true;
    let g = nums[0];

    for (let i = 1; i < n; ++i) {
        const ng = gcd(g, nums[i]);
        pos[i] = ng !== g;
        g = ng;
    }

    return pos;
}

function calc(arr: number[]): number {
    const n = arr.length;
    const pre = Array(n);
    const suf = Array(n);

    pre[0] = arr[0];
    for (let i = 1; i < n; ++i) {
        pre[i] = gcd(pre[i - 1], arr[i]);
    }

    suf[n - 1] = arr[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        suf[i] = gcd(suf[i + 1], arr[i]);
    }

    let ans = 0;
    for (let i = 0; i + 1 < n; ++i) {
        if (pre[i] === suf[i + 1]) {
            ++ans;
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    while (b !== 0) {
        [a, b] = [b, a % b];
    }
    return a;
}
