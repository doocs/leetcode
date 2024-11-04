function compressedString(word: string): string {
    const regex = /(.)\1{0,8}/g;
    let m: RegExpMatchArray | null = null;
    let res = '';

    while ((m = regex.exec(word))) {
        res += m[0].length + m[1];
    }

    return res;
}
