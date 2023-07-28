function doesValidArrayExist(derived: number[]): boolean {
    let s = 0;
    for (const x of derived) {
        s ^= x;
    }
    return s === 0;
}
