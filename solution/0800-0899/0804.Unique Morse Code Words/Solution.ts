const codes = [
    '.-',
    '-...',
    '-.-.',
    '-..',
    '.',
    '..-.',
    '--.',
    '....',
    '..',
    '.---',
    '-.-',
    '.-..',
    '--',
    '-.',
    '---',
    '.--.',
    '--.-',
    '.-.',
    '...',
    '-',
    '..-',
    '...-',
    '.--',
    '-..-',
    '-.--',
    '--..',
];

function uniqueMorseRepresentations(words: string[]): number {
    return new Set(
        words.map(word => {
            return word
                .split('')
                .map(c => codes[c.charCodeAt(0) - 'a'.charCodeAt(0)])
                .join('');
        }),
    ).size;
}
