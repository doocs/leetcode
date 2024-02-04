function minimumTimeToInitialState(word: string, k: number): number {
    const n = word.length;
    for (let i = 1; i <= 10000; i++) {
        const re = i * k;
        if (re >= n) {
            return i;
        }
        const str = word.substring(re);
        let flag = true;
        for (let j = 0; j < str.length; j++) {
            if (str[j] !== word[j]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return i;
        }
    }
    return 0;
}
