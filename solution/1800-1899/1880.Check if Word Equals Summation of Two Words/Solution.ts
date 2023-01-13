function isSumEqual(
    firstWord: string,
    secondWord: string,
    targetWord: string,
): boolean {
    const calc = (s: string) => {
        let res = 0;
        for (const c of s) {
            res = res * 10 + c.charCodeAt(0) - 'a'.charCodeAt(0);
        }
        return res;
    };
    return calc(firstWord) + calc(secondWord) === calc(targetWord);
}
