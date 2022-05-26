function nextGreatestLetter(letters: string[], target: string): string {
    let left = 0,
        right = letters.length;
    let x = target.charCodeAt(0);
    while (left < right) {
        let mid = (left + right) >> 1;
        if (x < letters[mid].charCodeAt(0)) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return letters[left % letters.length];
}
