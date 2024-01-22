class Solution {
public:
    long long minimumCost(vector<int>& nums, int k, int dist) {
        multiset<int> sml, big;
        int sz = dist + 1;
        long long sum = 0, ans = 0;
        for(int i = 1; i <= sz; i++){
            sml.insert(nums[i]);
            sum += nums[i];
        }
        while(sml.size() > k-1){
            big.insert(*sml.rbegin());
            sum -= *sml.rbegin();
            sml.erase(sml.find(*sml.rbegin()));
        }
        ans = sum;
        for(int i = sz+1; i < nums.size(); i++){
            sum += nums[i];
            sml.insert(nums[i]);
            if(big.find(nums[i - sz]) != big.end()){
                big.erase(big.find(nums[i - sz]));
            }
            else{
                sum -= nums[i - sz];
                sml.erase(sml.find(nums[i - sz]));
            }
            
            while(sml.size() > k-1){
                sum -= *sml.rbegin();
                big.insert(*sml.rbegin());
                sml.erase(sml.find(*sml.rbegin()));
            }
            while(sml.size() < k-1){
                sum += *big.begin();
                sml.insert(*big.begin());
                big.erase(big.begin());
            }
            while(!sml.empty() && !big.empty() && *sml.rbegin() > *big.begin()){
                sum -= *sml.rbegin() - *big.begin();
                sml.insert(*big.begin());
                big.insert(*sml.rbegin());
                sml.erase(sml.find(*sml.rbegin()));
                big.erase(big.begin());
            }
            ans = min(ans, sum);
        }
        int p = 0;
        return nums[0] + ans;
    }
};
