class Solution {
public:
    string getSmallestString(string s)
    {
        int i;
        int n=s.size();

        for(i=0;i<n-1;i++)
        {
            if((s[i]%2 == s[i+1]%2) && (s[i]>s[i+1]))
            {
                swap(s[i],s[i+1]);
                break;
            }
        }    
        return s;
    }
};