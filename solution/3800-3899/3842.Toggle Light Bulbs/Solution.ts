function toggleLightBulbs(bulbs: number[]): number[] {
    const st: number[] = new Array(101).fill(0);
    for (const x of bulbs) {
        st[x] ^= 1;
    }
    const ans: number[] = [];
    for (let i = 0; i < 101; i++) {
        if (st[i] === 1) {
            ans.push(i);
        }
    }
    return ans;
}
