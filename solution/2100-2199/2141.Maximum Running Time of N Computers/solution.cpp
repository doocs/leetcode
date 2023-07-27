class Solution {
 public:
  long long maxRunTime(int n, vector<int>& batteries) {
    long long sum = accumulate(batteries.begin(), batteries.end(), 0LL);

    sort(batteries.begin(), batteries.end());

    // Max battery is greater than the average, so it can last forever
    // Reduce the problem from size n to size n - 1
    while (batteries.back() > sum / n) {
      sum -= batteries.back(), batteries.pop_back();
      --n;
    }

    // If the max battery <= average running time,
    // It won't be waste, and so do smaller batteries
    return sum / n;
  }
};
