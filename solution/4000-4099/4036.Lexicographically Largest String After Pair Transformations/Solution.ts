function largestString(nums: number[]): string[] {
    const ans: string[] = [];
    for (let x of nums) {
        const s: string[] = [];
        for (let j = 25; j >= 0; --j) {
            const t = x >> j;
            s.push(String.fromCharCode(97 + j).repeat(t));
            x &= (1 << j) - 1;
        }
        ans.push(s.join(''));
    }
    return ans;
}
