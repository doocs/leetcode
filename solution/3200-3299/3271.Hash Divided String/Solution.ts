function stringHash(s: string, k: number): string {
    const ans: string[] = [];
    const n: number = s.length;

    for (let i = 0; i < n; i += k) {
        let t: number = 0;
        for (let j = i; j < i + k; j++) {
            t += s.charCodeAt(j) - 97;
        }
        const hashedChar: number = t % 26;
        ans.push(String.fromCharCode(97 + hashedChar));
    }

    return ans.join('');
}
