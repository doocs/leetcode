function countStudents(students: number[], sandwiches: number[]): number {
    const count = [0, 0];
    for (const v of students) {
        count[v]++;
    }
    for (const v of sandwiches) {
        if (count[v] === 0) {
            return count[v ^ 1];
        }
        count[v]--;
    }
    return 0;
}
