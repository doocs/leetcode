function dayOfTheWeek(d: number, m: number, y: number): string {
    if (m < 3) {
        m += 12;
        y -= 1;
    }
    const c: number = (y / 100) | 0;
    y %= 100;
    const w = (((c / 4) | 0) - 2 * c + y + ((y / 4) | 0) + (((13 * (m + 1)) / 5) | 0) + d - 1) % 7;
    const weeks: string[] = [
        'Sunday',
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday',
    ];
    return weeks[(w + 7) % 7];
}
