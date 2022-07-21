function decodeMessage(key: string, message: string): string {
    let decodeMap = new Map();
    const m = key.length,
        n = 26;
    for (let i = 0, j = 0; i < m; i++) {
        let char = key.charAt(i);
        if (char != ' ' && !decodeMap.has(char)) {
            decodeMap.set(char, String.fromCharCode(j + 97));
            j++;
        }
    }
    let ans = [];
    for (let char of message) {
        ans.push(char == ' ' ? ' ' : decodeMap.get(char));
    }
    return ans.join('');
}
