function vowelStrings(words: string[], queries: number[][]): number[] {
    const vowels = new Set(['a', 'e', 'i', 'o', 'u']);
    const nums: number[] = [];
    for (let i = 0; i < words.length; ++i) {
        if (vowels.has(words[i][0]) && vowels.has(words[i][words[i].length - 1])) {
            nums.push(i);
        }
    }
    const search = (x: number): number => {
        let l = 0,
            r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    return queries.map(([l, r]) => search(r + 1) - search(l));
}
