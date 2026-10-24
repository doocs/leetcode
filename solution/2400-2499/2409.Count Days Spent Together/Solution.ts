const f = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];

function countDaysTogether(
    arriveAlice: string,
    leaveAlice: string,
    arriveBob: string,
    leaveBob: string,
): number {
    const start = arriveAlice > arriveBob ? arriveAlice : arriveBob;
    const end = leaveAlice < leaveBob ? leaveAlice : leaveBob;
    const d1 = f[Number(start.slice(0, 2)) - 1] + Number(start.slice(3));
    const d2 = f[Number(end.slice(0, 2)) - 1] + Number(end.slice(3));
    return Math.max(d2 - d1 + 1, 0);
}
