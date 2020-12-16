class Solution {
public:
    vector<int> grayCode(int n) {
        vector<int> res;
        for (int i = 0; i < (1 << n); ++i) {
        	res.push_back((i >> 1) ^ i);
        }
        return res;
        
    }
};