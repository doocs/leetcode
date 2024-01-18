function numberOfWays(corridor: string): number {
    const M: number = 1e9 + 7;
    const seatNumbers: number[] = [];

    for (let i = 0; i < corridor.length; i++) {
        if (corridor.charAt(i) === 'S') {
            seatNumbers.push(i);
        }
    }

    if (seatNumbers.length % 2 !== 0 || seatNumbers.length === 0) {
        return 0;
    }

    let result: number = 1;

    for (let i = 2; i < seatNumbers.length; i += 2) {
        result = (result * (seatNumbers[i] - seatNumbers[i - 1])) % M;
    }

    return result;
}
