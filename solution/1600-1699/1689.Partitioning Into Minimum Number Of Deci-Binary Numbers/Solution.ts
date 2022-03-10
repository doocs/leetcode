function minPartitions(n: string): number {
    let nums = n.split('').map(d => parseInt(d));
    let ans = Math.max(...nums);
    return ans;
}
