class BinaryIndexedTree {
private:
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, int delta) {
        for (; x <= n; x += x & -x) {
            c[x] += delta;
        }
    }

    int query(int x) {
        int s = 0;
        for (; x > 0; x -= x & -x) {
            s += c[x];
        }
        return s;
    }
};

class Solution {
public:
    vector<int> resultArray(vector<int>& nums) {
        vector<int> st = nums;
        sort(st.begin(), st.end());
        int n = st.size();
        BinaryIndexedTree tree1(n + 1);
        BinaryIndexedTree tree2(n + 1);
        tree1.update(distance(st.begin(), lower_bound(st.begin(), st.end(), nums[0])) + 1, 1);
        tree2.update(distance(st.begin(), lower_bound(st.begin(), st.end(), nums[1])) + 1, 1);
        vector<int> arr1 = {nums[0]};
        vector<int> arr2 = {nums[1]};
        for (int k = 2; k < n; ++k) {
            int x = distance(st.begin(), lower_bound(st.begin(), st.end(), nums[k])) + 1;
            int a = arr1.size() - tree1.query(x);
            int b = arr2.size() - tree2.query(x);
            if (a > b) {
                arr1.push_back(nums[k]);
                tree1.update(x, 1);
            } else if (a < b) {
                arr2.push_back(nums[k]);
                tree2.update(x, 1);
            } else if (arr1.size() <= arr2.size()) {
                arr1.push_back(nums[k]);
                tree1.update(x, 1);
            } else {
                arr2.push_back(nums[k]);
                tree2.update(x, 1);
            }
        }
        arr1.insert(arr1.end(), arr2.begin(), arr2.end());
        return arr1;
    }
};