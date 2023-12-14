function numberOfRounds(startTime: string, finishTime: string): number {
    const f = (s: string): number => {
        const [h, m] = s.split(':').map(Number);
        return h * 60 + m;
    };
    let [a, b] = [f(startTime), f(finishTime)];
    if (a > b) {
        b += 1440;
    }
    return Math.max(0, Math.floor(b / 15) - Math.ceil(a / 15));
}
