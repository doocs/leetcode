function nextGreatestLetter(letters: string[], target: string): string {
    const n = letters.length;
    let left = 0;
    let right = letters.length;
    while (left < right) {
        let mid = (left + right) >>> 1;
        if (letters[mid] > target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return letters[left % n];
}
