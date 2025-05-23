class Solution {
public:
    string truncateSentence(string s, int k) {
        int count=0;
        string ans="";
        for(char i:s)
        {
            if(i==' ')
            {
                count++;
                if(count==k)
                break;
            }
            ans=ans+i;
        }
        return ans;

   
    }
};