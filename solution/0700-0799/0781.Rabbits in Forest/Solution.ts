function numRabbits(answers: number[]): number {
    const cnt = new Map<number, number>();
    for (const x of answers) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    let ans = 0;
    for (const [x, v] of cnt.entries()) {
        const group = x + 1;
        ans += Math.floor((v + group - 1) / group) * group;
    }
    return ans;
}
