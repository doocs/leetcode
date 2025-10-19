function findLexSmallestString(s: string, a: number, b: number): string {
    let ans = s;
    const n = s.length;
    let arr = s.split('');
    for (let _ = 0; _ < n; _++) {
        arr = arr.slice(-b).concat(arr.slice(0, -b));
        for (let j = 0; j < 10; j++) {
            for (let k = 1; k < n; k += 2) {
                arr[k] = String((Number(arr[k]) + a) % 10);
            }
            if (b & 1) {
                for (let p = 0; p < 10; p++) {
                    for (let k = 0; k < n; k += 2) {
                        arr[k] = String((Number(arr[k]) + a) % 10);
                    }
                    const t = arr.join('');
                    if (ans > t) {
                        ans = t;
                    }
                }
            } else {
                const t = arr.join('');
                if (ans > t) {
                    ans = t;
                }
            }
        }
    }
    return ans;
}
