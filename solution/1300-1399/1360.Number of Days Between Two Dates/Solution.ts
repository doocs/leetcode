function daysBetweenDates(date1: string, date2: string): number {
    return Math.abs(calcDays(date1) - calcDays(date2));
}

function isLeapYear(year: number): boolean {
    return year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0);
}

function daysOfMonth(year: number, month: number): number {
    const days = [31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
    return days[month - 1];
}

function calcDays(date: string): number {
    let days = 0;
    const [year, month, day] = date.split('-').map(Number);
    for (let y = 1971; y < year; ++y) {
        days += isLeapYear(y) ? 366 : 365;
    }
    for (let m = 1; m < month; ++m) {
        days += daysOfMonth(year, m);
    }
    days += day - 1;
    return days;
}
