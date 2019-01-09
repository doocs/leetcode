class Solution {

    int count = 0;

    public int totalNQueens(int n) {
        int[] c = new int[n];
        search(0, n, c);
        return count;
    }

    public void search(int cur, int n, int[] c) {
        if (cur == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            c[cur] = i;
            for (int j = 0; j < cur; j++) {
                if ((c[cur] == c[j]) || ((c[cur] - cur) == (c[j] - j)) || ((c[cur] + cur) == (c[j] + j))) {
                    flag = false;
                    break;
                }
            }
            if (flag) search(cur + 1, n, c);
        }
    }
}