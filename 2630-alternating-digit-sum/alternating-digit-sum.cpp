class Solution {
public:
    int alternateDigitSum(int n) {
        string s = to_string(n);
        int sum=0, fl=1;
        for(int i=0; i<s.size(); i++)
        {
            if(fl) sum += s[i]-'0';
            else sum-= s[i]-'0';
            fl =1-fl;
        }
        return sum;
    }
};