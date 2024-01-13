class Solution {
    public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
        int n = colsum.length;
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            int a = 0, b = 0;
            if (colsum[j] == 2) {
                a = b = 1;
                upper--;
                lower--;
            } else if (colsum[j] == 1) {
                if (upper > lower) {
                    upper--;
                    a = 1;
                } else {
                    lower--;
                    b = 1;
                }
            }
            if (upper < 0 || lower < 0) {
                break;
            }
            first.add(a);
            second.add(b);
        }
        return upper == 0 && lower == 0 ? List.of(first, second) : List.of();
    }
}