function winnerOfGame(colors: string): boolean {
    const n = colors.length;
    let [a, b] = [0, 0];
    for (let i = 0, j = 0; i < n; i = j) {
        while (j < n && colors[j] === colors[i]) {
            ++j;
        }
        const m = j - i - 2;
        if (m > 0) {
            if (colors[i] === 'A') {
                a += m;
            } else {
                b += m;
            }
        }
    }
    return a > b;
}
