function countValidWords(sentence: string): number {
    let words = sentence.trim().split(/\s+/);
    let ans = 0;
    for (let word of words) {
        if (isValied(word)) {
            ans++;
        }
    }
    return ans;
}

function isValied(str: string): boolean {
    let n = str.length;
    let hasLine = false;
    for (let i = 0; i < n; i++) {
        const char = str.charAt(i);
        if (/^[0-9]$/.test(char)) {
            return false;
        }
        if (char == '-') {
            if (hasLine) return false;
            else {
                hasLine = true;
            }
            let pre = str.charAt(i - 1),
                post = str.charAt(i + 1);
            if (!/^[a-z]$/g.test(pre) || !/^[a-z]$/g.test(post)) {
                return false;
            }
        }
        if (/^[\!\.\,\s]$/.test(char) && i != n - 1) {
            return false;
        }
    }
    return true;
}
