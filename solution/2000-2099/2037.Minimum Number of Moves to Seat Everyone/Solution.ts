function minMovesToSeat(seats: number[], students: number[]): number {
    seats.sort((a, b) => a - b);
    students.sort((a, b) => a - b);
    return seats.reduce((acc, seat, i) => acc + Math.abs(seat - students[i]), 0);
}
