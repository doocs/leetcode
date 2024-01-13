class SparseVector {
public:
    unordered_map<int, int> d;

    SparseVector(vector<int>& nums) {
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i]) {
                d[i] = nums[i];
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    int dotProduct(SparseVector& vec) {
        auto a = d;
        auto b = vec.d;
        if (a.size() > b.size()) {
            swap(a, b);
        }
        int ans = 0;
        for (auto& [i, v] : a) {
            if (b.count(i)) {
                ans += v * b[i];
            }
        }
        return ans;
    }
};

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1(nums1);
// SparseVector v2(nums2);
// int ans = v1.dotProduct(v2);