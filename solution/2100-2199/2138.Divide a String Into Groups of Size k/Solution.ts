function divideString(s: string, k: number, fill: string): string[] {
    const ans: string[] = [];
    for (let i = 0; i < s.length; i += k) {
        ans.push(s.slice(i, i + k).padEnd(k, fill));
    }
    return ans;
}
