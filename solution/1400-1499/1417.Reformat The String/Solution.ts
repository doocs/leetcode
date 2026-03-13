function reformat(s: string): string {
    let a: string[] = [];
    let b: string[] = [];

    for (const c of s) {
        if (c >= 'a' && c <= 'z') a.push(c);
        else if (c >= '0' && c <= '9') b.push(c);
    }

    if (Math.abs(a.length - b.length) > 1) {
        return '';
    }

    if (a.length < b.length) {
        [a, b] = [b, a];
    }

    const ans: string[] = [];

    for (let i = 0; i < b.length; i++) {
        ans.push(a[i] + b[i]);
    }

    if (a.length > b.length) {
        ans.push(a[a.length - 1]);
    }

    return ans.join('');
}
