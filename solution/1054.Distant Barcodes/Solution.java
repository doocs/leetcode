class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int x : barcodes) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        Data[] ds = new Data[map.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ds[i++] = new Data(entry.getKey(), entry.getValue());
        }
        Arrays.sort(ds);
        i = 0;
        for (Data d : ds) {
            while (d.cnt-- > 0) {
                barcodes[i] = d.x;
                i += 2;
                if (i >= barcodes.length) {
                    i = 1;
                }
            }
        }
        return barcodes;
    }

    class Data implements Comparable<Data> {
        int x, cnt;

        public Data(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Data o) {
            return Integer.compare(o.cnt, cnt);
        }
    }
}
