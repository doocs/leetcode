function aggregateTimeSeries(series1: number[][], series2: number[][]): number[][] {
    const m = series1.length;
    const n = series2.length;
    let i = 0;
    let j = 0;
    const ans: number[][] = [];

    while (i < m && j < n) {
        const [t1, v1] = series1[i];
        const [t2, v2] = series2[j];

        if (t1 === t2) {
            ans.push([t1, v1 + v2]);
            i++;
            j++;
        } else if (t1 < t2) {
            ans.push([t1, v1 + v2]);
            i++;
        } else {
            ans.push([t2, v1 + v2]);
            j++;
        }
    }

    while (i < m) {
        ans.push(series1[i]);
        i++;
    }

    while (j < n) {
        ans.push(series2[j]);
        j++;
    }

    return ans;
}
