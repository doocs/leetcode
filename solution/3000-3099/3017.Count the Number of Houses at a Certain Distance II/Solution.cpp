class Solution {
public:
  vector<long long> countOfPairs(int n, int x, int y) {
    --x, --y;
    if (x > y) {
      swap(x, y);
    }
    vector<long long> diff(n);
    for (int i = 0; i < n; ++i) {
      diff[0] += 1 + 1;
      ++diff[min(abs(i - x), abs(i - y) + 1)];
      ++diff[min(abs(i - y), abs(i - x) + 1)];
      --diff[min(abs(i - 0), abs(i - y) + 1 + abs(x - 0))];
      --diff[min(abs(i - (n - 1)), abs(i - x) + 1 + abs(y - (n - 1)))];
      --diff[max(x - i, 0) + max(i - y, 0) + ((y - x) + 0) / 2];
      --diff[max(x - i, 0) + max(i - y, 0) + ((y - x) + 1) / 2];
    }
    for (int i = 0; i + 1 < n; ++i) {
      diff[i + 1] += diff[i];
    }
    return diff;
  }
};
