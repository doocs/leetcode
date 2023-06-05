function arrangeWords(text: string): string {
    let words: string[] = text.split(' ');
    words[0] = words[0].toLowerCase();
    words.sort((a, b) => a.length - b.length);
    words[0] = words[0].charAt(0).toUpperCase() + words[0].slice(1);
    return words.join(' ');
}
