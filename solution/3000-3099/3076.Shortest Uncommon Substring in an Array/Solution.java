class Solution {
    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        String[] ans = new String[n];
        Arrays.fill(ans, "");
        for (int i = 0; i < n; ++i) {
            int m = arr[i].length();
            for (int j = 1; j <= m && ans[i].isEmpty(); ++j) {
                for (int l = 0; l <= m - j; ++l) {
                    String sub = arr[i].substring(l, l + j);
                    if (ans[i].isEmpty() || sub.compareTo(ans[i]) < 0) {
                        boolean ok = true;
                        for (int k = 0; k < n && ok; ++k) {
                            if (k != i && arr[k].contains(sub)) {
                                ok = false;
                            }
                        }
                        if (ok) {
                            ans[i] = sub;
                        }
                    }
                }
            }
        }
        return ans;
    }
}