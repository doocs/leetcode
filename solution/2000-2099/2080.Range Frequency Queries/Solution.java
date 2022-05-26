class RangeFreqQuery {
    private Map<Integer, List<Integer>> mp = new HashMap<>();

    public RangeFreqQuery(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            mp.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }
    }
    
    public int query(int left, int right, int value) {
        if (!mp.containsKey(value)) {
            return 0;
        }
        List<Integer> arr = mp.get(value);
        int l = search(arr, left - 1);
        int r = search(arr, right);
        return r - l;
    }

    private int search(List<Integer> arr, int val) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) > val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */