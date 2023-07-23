function sortVowels(s: string): string {
    const vowels = ['a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'];
    const vs = s
        .split('')
        .filter(c => vowels.includes(c))
        .sort();
    const ans: string[] = [];
    let j = 0;
    for (const c of s) {
        ans.push(vowels.includes(c) ? vs[j++] : c);
    }
    return ans.join('');
}
