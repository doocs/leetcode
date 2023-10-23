function maximumOr(nums: number[], k: number): number {
    const n = nums.length;
    const suf: bigint[] = Array(n + 1).fill(0n);
    for (let i = n - 1; i >= 0; i--) {
        suf[i] = suf[i + 1] | BigInt(nums[i]);
    }
    let [ans, pre] = [0, 0n];
    for (let i = 0; i < n; i++) {
        ans = Math.max(Number(ans), Number(pre | (BigInt(nums[i]) << BigInt(k)) | suf[i + 1]));
        pre |= BigInt(nums[i]);
    }
    return ans;
}
