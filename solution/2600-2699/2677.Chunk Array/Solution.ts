function chunk(arr: any[], size: number): any[][] {
    const ans: any[][] = [];
    for (let i = 0, n = arr.length; i < n; i += size) {
        ans.push(arr.slice(i, i + size));
    }
    return ans;
}
