function replaceSpace(s: string): string {
    const strArr = [];
    for (const c of s) {
        strArr.push(c === ' ' ? '%20' : c);
    }
    return strArr.join('');
}
