class Solution {
    public int[] countTasks(int[] tasks, int[] shifts) {
        int m = tasks.length;
        int n = shifts.length;

        long[] s = new long[m + 1];
        for (int i = 0; i < m; i++) {
            s[i + 1] = s[i] + tasks[i];
        }

        int[] ans = new int[n];

        int i = 0;
        long cur = 0;

        for (int j = 0; j < n; j++) {
            if (shifts[j] < tasks[i] - cur) {
                cur += shifts[j];
                ans[j] = m - i;
            } else {
                long t = shifts[j] - (tasks[i] - cur);

                if (t >= s[m] - s[i + 1]) {
                    i = 0;
                    cur = 0;
                } else {
                    int l = i + 1, r = m;

                    while (l < r) {
                        int mid = (l + r) >> 1;
                        if (t < s[mid + 1] - s[i + 1]) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }

                    cur = t - (s[l] - s[i + 1]);
                    i = l;
                    ans[j] = m - i;
                }
            }
        }

        return ans;
    }
}