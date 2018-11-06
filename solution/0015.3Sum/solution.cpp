class Solution {
public:
     vector<vector<int> > threeSum(vector<int> &num) {
        return threeSumGen(num, 0);
    }
    
    vector<vector<int> > threeSumGen(vector<int> &num, int target) {
        vector<vector<int>> allSol;
        if(num.size()<3) return allSol;
        sort(num.begin(),num.end());
        
        for(int i=0; i<num.size()-2; i++) {
            if(i>0 && num[i]==num[i-1]) continue; // 去重
            
            int left=i+1, right=num.size()-1;
            
            while(left<right) {
                int curSum = num[left]+num[right];
                int curTarget = target-num[i];
                if(curSum==curTarget) {
                    vector<int> sol;
                    sol.push_back(num[i]);
                    sol.push_back(num[left]);
                    sol.push_back(num[right]);
                    allSol.push_back(sol);
                    left++;
                    right--;
                    while(num[left]==num[left-1]) left++;
                    while(num[right]==num[right+1]) right--;
                }
                else if(curSum<curTarget)
                    left++;
                else
                    right--;
            }
        }
        return allSol;
    }
    
    
    
//backtrace TLE  
    
// 	vector<vector<int>> threeSum(vector<int>& nums) {
// 		sort(nums.begin(), nums.end());
// 		if (nums.size() <= 2 || nums[0] > 0) return{};
// 		set<vector<int>> res;
// 		for (int i = 0; i < nums.size(); i++)
// 		{
// 			int target = 0 - nums[i];
// 			int m = 0, n = nums.size() - 1;
// 			while (m < n)
// 			{
// 				while (true)
// 				{
//                     if (m >= n) break;
// 					if (m == i)
// 					{
// 						m++;
// 						continue;
// 					}
// 					if (n == i)
// 					{
// 						n--;
// 						continue;
// 					}
// 					if (nums[m] + nums[n] == target) {
// 						vector<int> s = { nums[m++], nums[i], nums[n] };
// 						sort(s.begin(), s.end());
//  						res.insert(s);
// 						break;
// 					}
// 					if (nums[m] + nums[n] > target) n--;
// 					if (nums[m] + nums[n] < target) m++;
// 				}
// 			}
			
// 		}
// 		vector<vector<int>> fres(res.begin(), res.end());
// 		return fres;
// 	}
};

// accelerate the cin process.
int x = []() {
    std::ios::sync_with_stdio(false);
    cin.tie(NULL);
    return 0;
}();
