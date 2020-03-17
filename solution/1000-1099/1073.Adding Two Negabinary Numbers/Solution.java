class Solution {
    public int[] addNegabinary(int[] arr1, int[] arr2) {
        List<Integer> list = new ArrayList<>();
        int carry = 0;
        for (int i = arr1.length - 1, j = arr2.length - 1; i >= 0 || j >= 0 || carry != 0; --i, --j) {
            carry += (i >= 0 ? arr1[i] : 0) + (j >= 0 ? arr2[j] : 0);
            list.add(carry & 1);
            carry = -(carry >> 1);
        }
        while (list.size() > 1 && list.get(list.size() - 1) == 0) {
            list.remove(list.size() - 1);
        }
        Collections.reverse(list);
        return list.stream().mapToInt(x -> x).toArray();
    }
}
