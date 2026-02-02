function nextGreatestLetter(letters: string[], target: string): string {
    const idx = _.sortedIndex(letters, target + '\0');
    return letters[idx % letters.length];
}
