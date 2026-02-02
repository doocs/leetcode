function wordSquares(words: string[]): string[][] {
    words.sort();
    const n = words.length;
    const ans: string[][] = [];

    for (let i = 0; i < n; i++) {
        const top = words[i];
        for (let j = 0; j < n; j++) {
            if (j !== i) {
                const left = words[j];
                for (let k = 0; k < n; k++) {
                    if (k !== j && k !== i) {
                        const right = words[k];
                        for (let h = 0; h < n; h++) {
                            if (h !== k && h !== j && h !== i) {
                                const bottom = words[h];
                                if (
                                    top[0] === left[0] &&
                                    top[3] === right[0] &&
                                    bottom[0] === left[3] &&
                                    bottom[3] === right[3]
                                ) {
                                    ans.push([top, left, right, bottom]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    return ans;
}
