function resultGrid(image: number[][], threshold: number): number[][] {
    const n: number = image.length;
    const m: number = image[0].length;
    const ans: number[][] = new Array(n).fill(0).map(() => new Array(m).fill(0));
    const ct: number[][] = new Array(n).fill(0).map(() => new Array(m).fill(0));
    for (let i = 0; i + 2 < n; ++i) {
        for (let j = 0; j + 2 < m; ++j) {
            let region: boolean = true;
            for (let k = 0; k < 3; ++k) {
                for (let l = 0; l < 2; ++l) {
                    region &&= Math.abs(image[i + k][j + l] - image[i + k][j + l + 1]) <= threshold;
                }
            }
            for (let k = 0; k < 2; ++k) {
                for (let l = 0; l < 3; ++l) {
                    region &&= Math.abs(image[i + k][j + l] - image[i + k + 1][j + l]) <= threshold;
                }
            }
            if (region) {
                let tot: number = 0;

                for (let k = 0; k < 3; ++k) {
                    for (let l = 0; l < 3; ++l) {
                        tot += image[i + k][j + l];
                    }
                }
                for (let k = 0; k < 3; ++k) {
                    for (let l = 0; l < 3; ++l) {
                        ct[i + k][j + l]++;
                        ans[i + k][j + l] += Math.floor(tot / 9);
                    }
                }
            }
        }
    }
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < m; ++j) {
            if (ct[i][j] === 0) {
                ans[i][j] = image[i][j];
            } else {
                ans[i][j] = Math.floor(ans[i][j] / ct[i][j]);
            }
        }
    }
    return ans;
}
