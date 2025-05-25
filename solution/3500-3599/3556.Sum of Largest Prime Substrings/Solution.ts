function sumOfLargestPrimes(s: string): number {
    const st = new Set<number>();
    const n = s.length;

    for (let i = 0; i < n; i++) {
        let x = 0;
        for (let j = i; j < n; j++) {
            x = x * 10 + Number(s[j]);
            if (isPrime(x)) {
                st.add(x);
            }
        }
    }

    const sorted = Array.from(st).sort((a, b) => a - b);
    const topThree = sorted.slice(-3);
    return topThree.reduce((sum, val) => sum + val, 0);
}

function isPrime(x: number): boolean {
    if (x < 2) return false;
    for (let i = 2; i * i <= x; i++) {
        if (x % i === 0) return false;
    }
    return true;
}
