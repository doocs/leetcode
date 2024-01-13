function longestDecomposition(text: string): number {
    let ans = 0;
    for (let i = 0, j = text.length - 1; i <= j; ) {
        let ok = false;
        for (let k = 1; i + k - 1 < j - k + 1; ++k) {
            if (text.slice(i, i + k) === text.slice(j - k + 1, j + 1)) {
                ans += 2;
                i += k;
                j -= k;
                ok = true;
                break;
            }
        }
        if (!ok) {
            ++ans;
            break;
        }
    }
    return ans;
}
