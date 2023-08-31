function numberOfDays(year: number, month: number): number {
    const leap = (year % 4 === 0 && year % 100 !== 0) || year % 400 === 0;
    const days = [0, 31, leap ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days[month];
}
