function maximumValue(strs: string[]): number {
    let ans = 0;
    for (const s of strs) {
        const num = Number(s);
        ans = Math.max(ans, Number.isNaN(num) ? s.length : num);
    }
    return ans;
}
