function angleClock(hour: number, minutes: number): number {
    const h = 30 * hour + 0.5 * minutes;
    const m = 6 * minutes;
    const diff = Math.abs(h - m);
    return Math.min(diff, 360 - diff);
}
