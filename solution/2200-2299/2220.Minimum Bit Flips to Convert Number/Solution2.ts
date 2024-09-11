function minBitFlips(start: number, goal: number): number {
    return (start ^ goal).toString(2).replace(/0/g, '').length;
}
