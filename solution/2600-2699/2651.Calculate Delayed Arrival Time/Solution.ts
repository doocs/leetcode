function findDelayedArrivalTime(
    arrivalTime: number,
    delayedTime: number,
): number {
    return (arrivalTime + delayedTime) % 24;
}
