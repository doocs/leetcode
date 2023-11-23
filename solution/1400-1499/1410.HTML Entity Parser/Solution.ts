function entityParser(text: string): string {
    const d: { [key: string]: string } = {
        '&quot;': '"',
        '&apos;': "'",
        '&amp;': '&',
        '&gt;': '>',
        '&lt;': '<',
        '&frasl;': '/',
    };

    const pattern = new RegExp(Object.keys(d).join('|'), 'g');
    return text.replace(pattern, match => d[match]);
}
