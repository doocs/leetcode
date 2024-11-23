/**
 * @param {character[][]} box
 * @return {character[][]}
 */
var rotateTheBox = function(box) {
    const m = box.length;
        const n = box[0].length;
        const ans = Array.from({ length: n }, () => Array(m).fill(null));
        
        for (let i = 0; i < m; i++) {
            for (let j = 0; j < n; j++) {
                ans[j][m - i - 1] = box[i][j];
            }
        }
        
        for (let j = 0; j < m; j++) {
            const q = [];
            for (let i = n - 1; i >= 0; i--) {
                if (ans[i][j] === '*') {
                    q.length = 0; 
                } else if (ans[i][j] === '.') {
                    q.push(i);
                } else if (q.length > 0) {
                    ans[q.shift()][j] = '#';
                    ans[i][j] = '.';
                    q.push(i); 
                }
            }
        }
        
        return ans;
};
