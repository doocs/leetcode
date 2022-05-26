class SparseVector {

    private Map<Integer, Integer> v;
    
    SparseVector(int[] nums) {
        v = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                v.put(i, nums[i]);
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        int res = 0;
        if (v.size() > vec.v.size()) {
            Map<Integer, Integer> t = v;
            v = vec.v;
            vec.v = t;
        }
        for (Map.Entry<Integer, Integer> entry : v.entrySet()) {
            int i = entry.getKey(), num = entry.getValue();
            res += num * vec.v.getOrDefault(i, 0);
        }
        return res;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);