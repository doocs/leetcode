class Solution {
public:
	vector<vector<int>> permute(vector<int>& nums) {
		if (nums.size() == 0) return{};
		vector<vector<int>> res;
		vector<int> curr_vec;
		vector<bool> used;
		for (int i = 0; i < nums.size(); i++)
		{
			used.push_back(false);
		}
		dfs(res, nums, 0, curr_vec, used);
		return res;
	}

	void dfs(vector<vector<int>>& res, vector<int>& nums, int depth, vector<int> curr_vec, vector<bool> used)
	{

		if (depth >= nums.size())
		{
			res.emplace_back(curr_vec);
			return;
		}

		for (int i = 0; i < nums.size(); i++)
		{
			if (used[i]) continue;
			used[i] = true;
			curr_vec.emplace_back(nums[i]);
			dfs(res, nums, depth + 1, curr_vec, used);
			curr_vec.pop_back();
			used[i] = false;
		}
	}
};
