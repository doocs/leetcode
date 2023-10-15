function lastVisitedIntegers(words: string[]): number[] {
    const nums: number[] = [];
    const ans: number[] = [];
    let k = 0;
    for (const w of words) {
        if (w === 'prev') {
            ++k;
            const i = nums.length - k;
            ans.push(i < 0 ? -1 : nums[i]);
        } else {
            k = 0;
            nums.push(+w);
        }
    }
    return ans;
}
