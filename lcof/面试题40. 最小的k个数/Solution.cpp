class Solution {
public:
    int partition(vector<int>& arr, int begin, int end) {
        int l = begin;
        int r = end;
        int povit = arr[begin];

        while (l<r) {
            while (l<r && arr[r]>=povit) {r--;}
            while (l<r && arr[l]<=povit) {l++;}
            if (l<r) {swap(arr[l], arr[r]);}
        }

        swap(arr[begin], arr[l]);
        return l;
    }

    void partSort(vector<int>& arr, int begin, int end, int target) {
        if (begin >= end) {
            return;
        }

        // 思路类似快排，这样做比堆排序时间复杂度低
        // C++中，stl提供partial_sort()方法，就是这种实现方式
        int mid = partition(arr, begin, end);
        if (mid == target) {
            return;
        } else if (target < mid) {
            partSort(arr, begin, mid-1, target);
        } else {
            partSort(arr, mid+1, end, target);
        }
        
        return;
    }

    vector<int> getLeastNumbers(vector<int>& arr, int k) {
        partSort(arr, 0, arr.size()-1, k-1);
        vector<int> ret(arr.begin(), arr.begin()+k);
        return ret;
    }
};