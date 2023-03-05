class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.length - 1, j = arr2.length - 1;
        List<Integer> ans = new ArrayList<>();
        for (int c = 0; i >= 0 || j >= 0 || c != 0; --i, --j) {
            int a = i < 0 ? 0 : arr1[i];
            int b = j < 0 ? 0 : arr2[j];
            int x = a + b + c;
            c = 0;
            if (x > 1) {
                x -= 2;
                c -= 1;
            }
            if (x < 0) {
                x += 2;
                c += 1;
            }
            ans.add(x);
        }
        while (ans.size() > 1 && ans.get(ans.size() - 1) == 0) {
            ans.remove(ans.size() - 1);
        }
        Collections.reverse(ans);
        return ans.stream().mapToInt(x -> x).toArray();
    }
}