class SnapshotArray {
    private List<int[]>[] arr;
    private int idx;

    public SnapshotArray(int length) {
        arr = new List[length];
        Arrays.setAll(arr, k -> new ArrayList<>());
    }

    public void set(int index, int val) {
        arr[index].add(new int[] {idx, val});
    }

    public int snap() {
        return idx++;
    }

    public int get(int index, int snap_id) {
        var vals = arr[index];
        int left = 0, right = vals.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (vals.get(mid)[0] > snap_id) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left == 0 ? 0 : vals.get(left - 1)[1];
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * SnapshotArray obj = new SnapshotArray(length);
 * obj.set(index,val);
 * int param_2 = obj.snap();
 * int param_3 = obj.get(index,snap_id);
 */