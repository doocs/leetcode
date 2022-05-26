class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] res = new char[n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[j][m - i - 1] = box[i][j];
            }
        }
        for (int j = 0; j < m; ++j) {
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = n - 1; i >= 0; --i) {
                if (res[i][j] == '*') {
                    q.clear();
                    continue;
                }
                if (res[i][j] == '.') {
                    q.offer(i);
                } else {
                    if (q.isEmpty()) {
                        continue;
                    }
                    res[q.poll()][j] = '#';
                    res[i][j] = '.';
                    q.offer(i);
                }
            }
        }
        return res;
    }
}