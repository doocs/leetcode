function maxStudentsOnBench(students: number[][]): number {
    const d: Map<number, Set<number>> = new Map();
    for (const [studentId, benchId] of students) {
        if (!d.has(benchId)) {
            d.set(benchId, new Set());
        }
        d.get(benchId)?.add(studentId);
    }
    let ans = 0;
    for (const s of d.values()) {
        ans = Math.max(ans, s.size);
    }
    return ans;
}
