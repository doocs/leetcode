function maxNumOfSubstrings(s: string): string[] {
    const n = s.length;
    const first = new Array(26).fill(n);
    const last = new Array(26).fill(-1);

    // Step 1: Find first and last occurrence of each character
    for (let i = 0; i < n; i++) {
        const c = s.charCodeAt(i) - 97;
        first[c] = Math.min(first[c], i);
        last[c] = Math.max(last[c], i);
    }

    // Step 2: Build minimal valid intervals
    const intervals: [number, number][] = [];

    for (let c = 0; c < 26; c++) {
        if (last[c] === -1) continue;

        let l = first[c];
        let r = last[c];
        let valid = true;

        let i = l;
        while (i <= r) {
            const ch = s.charCodeAt(i) - 97;
            if (first[ch] < l) {
                // Must extend left past our start — not a minimal valid substring
                valid = false;
                break;
            }
            r = Math.max(r, last[ch]);
            i++;
        }

        if (valid) {
            intervals.push([l, r]);
        }
    }

    // Step 3: Greedy interval scheduling — sort by right endpoint
    intervals.sort((a, b) => a[1] - b[1]);

    const result: string[] = [];
    let prevEnd = -1;

    for (const [l, r] of intervals) {
        if (l > prevEnd) {
            result.push(s.substring(l, r + 1));
            prevEnd = r;
        }
    }

    return result;
}
