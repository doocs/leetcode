function getHint(secret: string, guess: string): string {
    const cnt1: number[] = Array(10).fill(0);
    const cnt2: number[] = Array(10).fill(0);
    let x: number = 0;
    for (let i = 0; i < secret.length; ++i) {
        if (secret[i] === guess[i]) {
            ++x;
        } else {
            ++cnt1[+secret[i]];
            ++cnt2[+guess[i]];
        }
    }
    let y: number = 0;
    for (let i = 0; i < 10; ++i) {
        y += Math.min(cnt1[i], cnt2[i]);
    }
    return `${x}A${y}B`;
}
