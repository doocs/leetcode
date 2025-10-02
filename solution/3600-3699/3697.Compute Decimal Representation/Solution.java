class Solution {
    public int[] decimalRepresentation(int n) {
        List<Integer> parts = new ArrayList<>();
        int p = 1;
        while (n > 0) {
            int v = n % 10;
            n /= 10;
            if (v != 0) {
                parts.add(p * v);
            }
            p *= 10;
        }
        Collections.reverse(parts);
        int[] ans = new int[parts.size()];
        for (int i = 0; i < parts.size(); ++i) {
            ans[i] = parts.get(i);
        }
        return ans;
    }
}
