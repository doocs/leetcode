function topStudents(
    positive_feedback: string[],
    negative_feedback: string[],
    report: string[],
    student_id: number[],
    k: number,
): number[] {
    const n = student_id.length;
    const map = new Map<number, number>();
    const ps = new Set(positive_feedback);
    const ns = new Set(negative_feedback);
    for (let i = 0; i < n; i++) {
        map.set(
            student_id[i],
            report[i].split(' ').reduce((r, s) => {
                if (ps.has(s)) {
                    return r + 3;
                }
                if (ns.has(s)) {
                    return r - 1;
                }
                return r;
            }, 0),
        );
    }
    return [...map.entries()]
        .sort((a, b) => {
            if (a[1] === b[1]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        })
        .map(v => v[0])
        .slice(0, k);
}
