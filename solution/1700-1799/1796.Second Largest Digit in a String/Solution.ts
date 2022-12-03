function secondHighest(s: string): number {
    let first = -1;
    let second = -1;
    for (const c of s) {
        if (c >= '0' && c <= '9') {
            const num = c.charCodeAt(0) - '0'.charCodeAt(0);
            if (first < num) {
                [first, second] = [num, first];
            } else if (first !== num && second < num) {
                second = num;
            }
        }
    }
    return second;
}
