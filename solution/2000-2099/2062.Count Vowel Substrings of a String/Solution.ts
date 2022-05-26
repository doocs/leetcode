function countVowelSubstrings(word: string): number {
    const n = word.length;
    let left = 0,
        right = 0;
    let ans = 0;
    while (right < n) {
        if (!isVowel(word.charAt(right))) {
            // 移动左指针
            left = right + 1;
        } else {
            let cur = word.substring(left, right + 1).split('');
            while (cur.length > 0) {
                if (isValiedArr(cur)) {
                    ans++;
                }
                cur.shift();
            }
        }
        right++;
    }
    return ans;
}

function isVowel(char: string): boolean {
    return ['a', 'e', 'i', 'o', 'u'].includes(char);
}

function isValiedArr(arr: Array<string>): boolean {
    return new Set(arr).size == 5;
}
