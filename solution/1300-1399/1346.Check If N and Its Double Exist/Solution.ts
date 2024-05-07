function checkIfExist(arr: number[]): boolean {
    const s: Set<number> = new Set();
    for (const x of arr) {
        if (s.has(x * 2) || (x % 2 === 0 && s.has((x / 2) | 0))) {
            return true;
        }
        s.add(x);
    }
    return false;
}
