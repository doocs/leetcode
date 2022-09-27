function isUnique(astr: string): boolean {
    let mask = 0;
    for (let j = 0; j < astr.length; ++j) {
        const i = astr.charCodeAt(j) - 'a'.charCodeAt(0);
        if ((mask >> i) & 1) {
            return false;
        }
        mask |= 1 << i;
    }
    return true;
}
