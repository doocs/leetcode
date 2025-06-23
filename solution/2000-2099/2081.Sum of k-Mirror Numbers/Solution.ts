function kMirror(k: number, n: number): number {
    function check(x: number, k: number): boolean {
        const s: number[] = [];
        while (x > 0) {
            s.push(x % k);
            x = Math.floor(x / k);
        }
        for (let i = 0, j = s.length - 1; i < j; i++, j--) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    }

    let ans = 0;
    for (let l = 1; ; l++) {
        const x = Math.pow(10, Math.floor((l - 1) / 2));
        const y = Math.pow(10, Math.floor((l + 1) / 2));
        for (let i = x; i < y; i++) {
            let v = i;
            let j = l % 2 === 0 ? i : Math.floor(i / 10);
            while (j > 0) {
                v = v * 10 + (j % 10);
                j = Math.floor(j / 10);
            }
            if (check(v, k)) {
                ans += v;
                n--;
                if (n === 0) {
                    return ans;
                }
            }
        }
    }
}
