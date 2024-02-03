function minPartitions(n: string): number {
    return Math.max(...n.split('').map(d => parseInt(d)));
}
