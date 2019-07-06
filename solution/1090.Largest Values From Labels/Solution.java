class Solution {
    public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
        class Data implements Comparable<Data> {
            int value, label;

            public Data(int value, int label) {
                this.value = value;
                this.label = label;
            }

            @Override
            public int compareTo(Data o) {
                return Integer.compare(o.value, this.value);
            }
        }
        int n = values.length;
        Data[] ds = new Data[n];
        for (int i = 0; i < n; ++i) {
            ds[i] = new Data(values[i], labels[i]);
        }
        Arrays.sort(ds);
        int[] map = new int[20001];
        int res = 0;
        for (int i = 0; i < n && num_wanted != 0; ++i) {
            if (++map[ds[i].label] <= use_limit) {
                res += ds[i].value;
                --num_wanted;
            }
        }
        return res;
    }
}
