class Solution {
public:
    int maxProduct(int n) {
        vector<int>res;
        while(n>0)
        {
            res.push_back(n%10);
            n/=10;

 }
 sort(res.begin(), res.end());
 return res[res.size()-1] * res[res.size()-2];      
    }
};