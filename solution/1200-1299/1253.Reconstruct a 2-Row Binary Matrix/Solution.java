class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            if (colsum[j] == 2) {
                first.add(1);
                second.add(1);
                upper--;
                lower--;
            } else if (colsum[j] == 1) {
                if (upper > lower) {
                    upper--;
                    first.add(1);
                    second.add(0);
                } else {
                    lower--;
                    first.add(0);
                    second.add(1);
                }
            } else {
                first.add(0);
                second.add(0);
            }
            if (upper < 0 || lower < 0) {
                return ans;
            }
        }
        if (upper != 0 || lower != 0) {
            return ans;
        }
        ans.add(first);
        ans.add(second);
        return ans;
    }
}