function closetTarget(
    words: string[],
    target: string,
    startIndex: number,
): number {
    const n = words.length;
    for (let i = 0; i <= n >> 1; i++) {
        if (
            words[(startIndex - i + n) % n] === target ||
            words[(startIndex + i) % n] === target
        ) {
            return i;
        }
    }
    return -1;
}
