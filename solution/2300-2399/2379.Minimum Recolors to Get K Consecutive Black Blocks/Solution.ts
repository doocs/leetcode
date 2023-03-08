function minimumRecolors(blocks: string, k: number): number {
    let cnt = 0;
    for (let i = 0; i < k; ++i) {
        cnt += blocks[i] === 'W' ? 1 : 0;
    }
    let ans = cnt;
    for (let i = k; i < blocks.length; ++i) {
        cnt += blocks[i] === 'W' ? 1 : 0;
        cnt -= blocks[i - k] === 'W' ? 1 : 0;
        ans = Math.min(ans, cnt);
    }
    return ans;
}
