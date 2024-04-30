function numberOfEmployeesWhoMetTarget(hours: number[], target: number): number {
    return hours.filter(x => x >= target).length;
}
