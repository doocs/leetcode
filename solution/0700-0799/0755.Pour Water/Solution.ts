function pourWater(heights: number[], volume: number, k: number): number[] {
    while (volume-- > 0) {
        let find = false;
        for (let d = -1; d < 2 && !find; d += 2) {
            let i = k,
                j = k;
            while (i + d >= 0 && i + d < heights.length && heights[i + d] <= heights[i]) {
                if (heights[i + d] < heights[i]) {
                    j = i + d;
                }
                i += d;
            }
            if (j !== k) {
                find = true;
                ++heights[j];
            }
        }
        if (!find) {
            ++heights[k];
        }
    }
    return heights;
}
