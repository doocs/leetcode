function minimumHealth(damage: number[], armor: number): number {
    let s = 0;
    let mx = 0;
    for (const v of damage) {
        mx = Math.max(mx, v);
        s += v;
    }
    return s - Math.min(mx, armor) + 1;
}
