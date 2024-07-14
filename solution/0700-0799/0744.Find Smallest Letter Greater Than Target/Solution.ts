function nextGreatestLetter(letters: string[], target: string): string {
    let [l, r] = [0, letters.length];
    while (l < r) {
        const mid = (l + r) >> 1;
        if (letters[mid] > target) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return letters[l % letters.length];
}
