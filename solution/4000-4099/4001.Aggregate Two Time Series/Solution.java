class Solution {
    public List<List<Integer>> aggregateTimeSeries(int[][] series1, int[][] series2) {
        int m = series1.length, n = series2.length;
        int i = 0, j = 0;
        List<List<Integer>> ans = new ArrayList<>();

        while (i < m && j < n) {
            int t1 = series1[i][0], v1 = series1[i][1];
            int t2 = series2[j][0], v2 = series2[j][1];

            if (t1 == t2) {
                ans.add(List.of(t1, v1 + v2));
                i++;
                j++;
            } else if (t1 < t2) {
                ans.add(List.of(t1, v1 + v2));
                i++;
            } else {
                ans.add(List.of(t2, v1 + v2));
                j++;
            }
        }

        while (i < m) {
            ans.add(List.of(series1[i][0], series1[i][1]));
            i++;
        }

        while (j < n) {
            ans.add(List.of(series2[j][0], series2[j][1]));
            j++;
        }

        return ans;
    }
}