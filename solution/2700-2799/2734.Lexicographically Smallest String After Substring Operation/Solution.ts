function smallestString(s: string): string {
    const cs: string[] = s.split('');
    const n: number = cs.length;
    let i: number = 0;
    while (i < n && cs[i] === 'a') {
        i++;
    }

    if (i === n) {
        cs[n - 1] = 'z';
        return cs.join('');
    }

    let j: number = i;
    while (j < n && cs[j] !== 'a') {
        const c: number = cs[j].charCodeAt(0);
        cs[j] = String.fromCharCode(c - 1);
        j++;
    }

    return cs.join('');
}
