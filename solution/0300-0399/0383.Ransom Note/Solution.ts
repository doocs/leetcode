function canConstruct(ransomNote: string, magazine: string): boolean {
    let counter = new Array(26).fill(0);
    let base = 'a'.charCodeAt(0);
    for (let s of magazine) {
        ++counter[s.charCodeAt(0) - base];
    }
    for (let s of ransomNote) {
        let idx = s.charCodeAt(0) - base;
        if (counter[idx] == 0) return false;
        --counter[idx];
    }
    return true;
}
