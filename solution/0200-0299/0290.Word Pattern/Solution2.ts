function wordPattern(pattern: string, s: string): boolean {
    const hash: Record<string, string> = Object.create(null);
    const arr = s.split(/\s+/);

    if (pattern.length !== arr.length || new Set(pattern).size !== new Set(arr).size) {
        return false;
    }

    for (let i = 0; i < pattern.length; i++) {
        hash[pattern[i]] ??= arr[i];
        if (hash[pattern[i]] !== arr[i]) {
            return false;
        }
    }

    return true;
}
