class Solution {
public:
    vector<int> secondGreaterElement(vector<int>& nums) {
        vector<int> secondNextGreater(nums.size(), -1);

        // Decreasing monotonic stacks: {num, idx}.
        stack<pair<int, int>> stackOne, stackTwo;

        vector<pair<int, int>> transporter; // Format: {num, idx}.
        
        for (int idx = 0; idx < nums.size(); idx++) {
            int num = nums[idx];

            while (!stackTwo.empty() && stackTwo.top().first < num) {
                int past_idx = stackTwo.top().second;
                secondNextGreater[past_idx] = num;
                stackTwo.pop();
            }

            while (!stackOne.empty() && stackOne.top().first < num) {
                transporter.push_back(stackOne.top());  // Keep decreasing monotonicity.
                stackOne.pop();
            }

            while (!transporter.empty()) {
                stackTwo.push(transporter.back());
                transporter.pop_back();
            }            
            
            stackOne.push({num, idx});
        }
        
        return secondNextGreater;
    }
};