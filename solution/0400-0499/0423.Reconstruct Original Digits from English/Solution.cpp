class Solution {
public:
    string originalDigits(string s) {
        vector<int> chs(26, 0) ;
        vector<int> nums(10, 0) ;
        for (char ch: s)
            ++chs[ch-'a'] ;
        
        if (chs['z' - 'a'] != 0)
        {
            const int cnt = chs['z' - 'a'] ;
            chs['e' - 'a'] -= cnt ;
            chs['r' - 'a'] -= cnt ;
            chs['o' - 'a'] -= cnt ;
            nums[0] = cnt ;
        }
        
        if (chs['w' - 'a'] != 0)
        {
            const int cnt = chs['w' - 'a'] ;
            chs['t' - 'a'] -= cnt ;
            chs['o' - 'a'] -= cnt ;
            nums[2] = cnt ;
        }
        
        if (chs['u' - 'a'] != 0)
        {
            const int cnt = chs['u' - 'a'] ;
            chs['f' - 'a'] -= cnt ;
            chs['o' - 'a'] -= cnt ;
            chs['r' - 'a'] -= cnt ;
            nums[4] = cnt ;
        }
        
        if (chs['x' - 'a'] != 0)
        {
            const int cnt = chs['x' - 'a'] ;
            chs['s' - 'a'] -= cnt ;
            chs['i' - 'a'] -= cnt ;
            nums[6] = cnt ;
        }
        
        if (chs['g' - 'a'] != 0)
        {
            const int cnt = chs['g' - 'a'] ;
            chs['e' - 'a'] -= cnt ;
            chs['i' - 'a'] -= cnt ;
            chs['h' - 'a'] -= cnt ;
            chs['t' - 'a'] -= cnt ;
            nums[8] = cnt ;
        }
        
        if (chs['o' - 'a'] != 0)
        {
            const int cnt = chs['o' - 'a'] ;
            chs['n' - 'a'] -= cnt ;
            chs['e' - 'a'] -= cnt ;
            nums[1] = cnt ;
        }
        
        if (chs['t' - 'a'] != 0)
        {
            const int cnt = chs['t' - 'a'] ;
            chs['h' - 'a'] -= cnt ;
            chs['r' - 'a'] -= cnt ;
            chs['e' - 'a']  -= cnt*2 ;
            nums[3] = cnt ;
        }
        
        if (chs['f' - 'a'] != 0)
        {
            const int cnt = chs['f' - 'a'] ;
            chs['i' - 'a'] -= cnt ;
            chs['v' - 'a'] -= cnt ;
            chs['e' - 'a'] -= cnt ;
            nums[5] = cnt ;
        }
        
        if (chs['s' - 'a'] != 0)
        {
            const int cnt = chs['s' - 'a'] ;
            chs['v' - 'a'] -= cnt ;
            chs['n' - 'a'] -= cnt ;
            chs['e' - 'a']  -= cnt*2 ;
            nums[7] = cnt ;
        }
        
        nums[9] = chs['i' - 'a'] ;
        
        stringstream ss ;
        for (int i = 0; i < 10; ++i)
            while (nums[i]--)
                ss << char('0' + i) ;
        
        string res ;
        ss >> res ;
        return res ;
    }
};
