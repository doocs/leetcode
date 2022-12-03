function mostWordsFound(sentences: string[]): number {
    return sentences.reduce(
        (r, s) =>
            Math.max(
                r,
                [...s].reduce((r, c) => r + (c === ' ' ? 1 : 0), 1),
            ),
        0,
    );
}
