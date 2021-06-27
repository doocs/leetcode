class Solution {
public:
    vector<int> arraysIntersection(vector<int>& arr1, vector<int>& arr2, vector<int>& arr3) {
        vector<int> res;
        for (int num : arr1) {
            if (find(arr2, num) && find(arr3, num)) {
                res.push_back(num);
            }
        }
        return res;
    }

private:
    bool find(vector<int>& arr, int val) {
        int left = 0, right = arr.size() - 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (arr[mid] >= val) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return arr[left] == val;
    }
};