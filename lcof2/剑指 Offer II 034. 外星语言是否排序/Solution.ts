function isAlienSorted(words: string[], order: string): boolean {
    let charMap = new Map();
    for (let i = 0; i < order.length; i++) {
        charMap.set(order[i], i);
    }
    function compare(str1: string, str2: string): boolean {
        const n = Math.min(str1.length, str2.length);
        for (let i = 0; i < n; i++) {
            let k1 = str1[i],
                k2 = str2[i];
            if (k1 != k2) return charMap.get(k1) < charMap.get(k2);
        }
        return n == str1.length;
    }
    for (let i = 1; i < words.length; i++) {
        if (!compare(words[i - 1], words[i])) return false;
    }
    return true;
}
