function uncommonFromSentences(s1: string, s2: string): string[] {
    let hashMap: Map<string, number> = new Map();
    for (let str of [...s1.split(' '), ...s2.split(' ')]) {
        hashMap.set(str, (hashMap.get(str) || 0) + 1);
    }
    let ans: Array<string> = [];
    for (let [key, count] of hashMap.entries()) {
        if (count == 1) {
            ans.push(key);
        }
    }
    return ans;
}
