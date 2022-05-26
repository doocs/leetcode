class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] map = new int[1001];
        for (int x : arr1) {
            ++map[x];
        }
        int i = 0;
        for (int x : arr2) {
            while (map[x]-- > 0) {
                arr1[i++] = x;
            }
        }
        for (int j = 0; j < map.length; ++j) {
            while (map[j]-- > 0) {
                arr1[i++] = j;
            }
        }
        return arr1;
    }
}
