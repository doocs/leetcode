function numberOfWeeks(milestones: number[]): number {
    const mx = Math.max(...milestones);
    const s = milestones.reduce((a, b) => a + b, 0);
    const rest = s - mx;
    return mx > rest + 1 ? rest * 2 + 1 : s;
}
