function minLengthAfterRemovals(s: string): number {
    let a = 0;
    for (const c of s) {
        if (c === 'a') {
            ++a;
        }
    }
    const b = s.length - a;
    return Math.abs(a - b);
}
