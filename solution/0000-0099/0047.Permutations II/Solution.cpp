class Solution {
public:
	vector<vector<int>> permuteUnique(vector<int>& nums) {
		if (nums.size() <= 0) return{};

		sort(nums.begin(), nums.end());
		
		vector<vector<int>> res;
		vector<bool> used;
		vector<int> curr;
		
		for (auto item : nums)
		{
			used.push_back(false);
		}

		dfs(res, nums, curr, used, 0);
		return res;
	}

	void dfs(vector<vector<int>>& res, vector<int>& nums, vector<int> curr, vector<bool> used, int depth)
	{
		if (depth >= nums.size())
		{
			res.emplace_back(curr);
			return;
		}

		for (int i = 0; i < nums.size(); i++)
		{
			if (used[i]) continue; 
			if (i > 0 && nums[i] == nums[i - 1] && !used[i-1]) continue;

			used[i] = true;
			curr.emplace_back(nums[i]);
			dfs(res, nums, curr, used, depth + 1);
			curr.pop_back();
			used[i] = false;
		}
	}
};
