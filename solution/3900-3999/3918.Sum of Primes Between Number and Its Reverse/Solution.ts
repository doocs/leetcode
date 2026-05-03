const LIMIT = 1000;
const isPrime: boolean[] = new Array(LIMIT + 1).fill(true);
isPrime[0] = isPrime[1] = false;
for (let i = 2; i * i <= LIMIT; i++) {
    if (isPrime[i]) {
        for (let j = i * i; j <= LIMIT; j += i) {
            isPrime[j] = false;
        }
    }
}

function sumOfPrimesInRange(n: number): number {
    const r = parseInt(n.toString().split('').reverse().join(''));
    const low = Math.min(n, r);
    const high = Math.max(n, r);
    let sum = 0;
    for (let x = low; x <= high; x++) {
        if (isPrime[x]) sum += x;
    }
    return sum;
}
