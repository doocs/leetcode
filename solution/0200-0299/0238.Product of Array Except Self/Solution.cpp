class Solution
{
public:
  vector<int> productExceptSelf(vector<int> &nums)
  {
    vector<int> dpLeft(nums.size(), 1);
    vector<int> dpRight(nums.size(), 1);
    for (int i = 1; i < nums.size(); i++)
    {
      dpLeft[i] = dpLeft[i - 1] * nums[i - 1];
    }
    for (int i = nums.size() - 2; i >= 0; i--)
    {
      dpRight[i] = dpRight[i + 1] * nums[i + 1];
    }
    vector<int> result;
    for (int i = 0; i < nums.size(); i++)
    {
      result.push_back(dpLeft[i] * dpRight[i]);
    }
    return result;
  }
};