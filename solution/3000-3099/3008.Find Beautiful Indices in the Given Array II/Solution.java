public class Solution {
    public void computeLPS(String pattern, int[] lps) {
        int M = pattern.length();
        int len = 0;

        lps[0] = 0;

        int i = 1;
        while (i < M) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    public List<Integer> KMP_codestorywithMIK(String pat, String txt) {
        int N = txt.length();
        int M = pat.length();
        List<Integer> result = new ArrayList<>();

        int[] lps = new int[M];
        computeLPS(pat, lps);

        int i = 0; // Index for text
        int j = 0; // Index for pattern

        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                i++;
                j++;
            }

            if (j == M) {
                result.add(i - j); // Pattern found at index i-j+1 (If you have to return 1 Based
                                   // indexing, that's why added + 1)
                j = lps[j - 1];
            } else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return result;
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1, result = list.size();

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        int n = s.length();

        List<Integer> i_indices = KMP_codestorywithMIK(a, s);
        List<Integer> j_indices = KMP_codestorywithMIK(b, s);

        List<Integer> result = new ArrayList<>();

        for (int i : i_indices) {

            int left_limit = Math.max(0, i - k); // To avoid out of bound -> I used max(0, i-k)
            int right_limit
                = Math.min(n - 1, i + k); // To avoid out of bound -> I used min(n-1, i+k)

            int lowerBoundIndex = lowerBound(j_indices, left_limit);

            if (lowerBoundIndex < j_indices.size()
                && j_indices.get(lowerBoundIndex) <= right_limit) {
                result.add(i);
            }
        }

        return result;
    }
}
