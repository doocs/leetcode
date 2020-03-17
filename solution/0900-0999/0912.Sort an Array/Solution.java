class Solution {

    void createHeap(int[] data, int n, int h) {
        int i = h;
        int j = 2 * i + 1;
        int temp = data[i];

        while (j < n) {
            if (j + 1 < n && data[j] < data[j + 1]) j++;
            if (temp > data[j]) {
                break;
            } else {
                data[i] = data[j];
                i = j;
                j = 2 * i + 1;
            }
        }
        data[i] = temp;
    }

    void initHeap(int[] data, int n) {
        for (int i = (n - 2) / 2; i > -1; i--) {
            createHeap(data, n, i);
        }
    }

    void heapSort(int[] data, int n) {
        initHeap(data, n);

        for (int i = n - 1;i > -1;i--) {
            int temp = data[i];
            data[i] = data[0];
            data[0] = temp;
            createHeap(data, i, 0);
        }
    }

    public int[] sortArray(int[] nums) {
        heapSort(nums, nums.length);
        return nums;
    }
}
