class Solution {
    public int findLatestStep(int[] arr, int m) {
        // 倒序遍历 arr，转换为第一次出现 m 个的步骤
        if (arr.length == m) {
            return m;
        }
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(arr.length + 1);
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = arr[i];
            int l = set.lower(index);
            int h = set.higher(index);
            if (index - l - 1 == m || h - index - 1 == m) {
                return i;
            }
            set.add(index);
        }
        return -1;
    }
}