class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int len = digits.size();
        if(len == 0)return digits;
        digits[len-1]++;
        int num = digits[len - 1];
        for(int i = len - 1;i>=1;i--){
            digits[i-1] = digits[i-1] + digits[i]/10;
            digits[i] %= 10;
        }

        if(digits[0] >= 10){
            digits.insert(digits.begin(),digits[0]/10);
            digits[1] = digits[1] % 10;   
        }
        return digits;
    }
};