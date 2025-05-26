class Solution {
  List<List<int>> divideArray(List<int> nums, int k) {
    nums.sort();
    List<List<int>> ans = [];

    for (int i = 0; i < nums.length; i += 3) {
      if (i + 2 >= nums.length) {
        return [];
      }

      List<int> t = nums.sublist(i, i + 3);
      if (t[2] - t[0] > k) {
        return [];
      }

      ans.add(t);
    }

    return ans;
  }
}
