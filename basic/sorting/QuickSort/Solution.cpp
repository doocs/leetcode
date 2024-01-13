#include <iostream>

using namespace std;

const int N = 1e6 + 10;

int n;
int nums[N];

void quick_sort(int nums[], int left, int right) {
    if (left >= right) return;
    int i = left - 1, j = right + 1;
    int x = nums[left + right >> 1];
    while (i < j) {
        while (nums[++i] < x)
            ;
        while (nums[--j] > x)
            ;
        if (i < j) swap(nums[i], nums[j]);
    }
    quick_sort(nums, left, j);
    quick_sort(nums, j + 1, right);
}

int main() {
    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; ++i) scanf("%d", &nums[i]);
    quick_sort(nums, 0, n - 1);
    for (int i = 0; i < n; ++i) printf("%d ", nums[i]);
}