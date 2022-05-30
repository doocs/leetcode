function sumSubarrayMins(arr: number[]): number {
    const n = arr.length;
    function getEle(i: number): number {
        if (i == -1 || i == n) return Number.MIN_SAFE_INTEGER;
        return arr[i];
    }
    let ans = 0;
    const mod = 10 ** 9 + 7;
    let stack = [];
    for (let i = -1; i <= n; i++) {
        while (stack.length && getEle(stack[0]) > getEle(i)) {
            const idx = stack.shift();
            ans = (ans + arr[idx] * (idx - stack[0]) * (i - idx)) % mod;
        }
        stack.unshift(i);
    }
    return ans;
}
