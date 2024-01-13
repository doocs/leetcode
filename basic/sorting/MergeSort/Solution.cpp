#include <iostream>

using namespace std;

const int N = 1e6 + 10;

int n;
int nums[N];
int tmp[N];

void merge_sort(int nums[], int left, int right) {
    if (left >= right) return;
    int mid = (left + right) >> 1;
    merge_sort(nums, left, mid);
    merge_sort(nums, mid + 1, right);
    int i = left, j = mid + 1, k = 0;
    while (i <= mid && j <= right) {
        if (nums[i] <= nums[j])
            tmp[k++] = nums[i++];
        else
            tmp[k++] = nums[j++];
    }
    while (i <= mid) tmp[k++] = nums[i++];
    while (j <= right) tmp[k++] = nums[j++];
    for (i = left, j = 0; i <= right; ++i, ++j) nums[i] = tmp[j];
}

int main() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) scanf("%d", &nums[i]);
    merge_sort(nums, 0, n - 1);
    for (int i = 0; i < n; ++i) printf("%d ", nums[i]);
}