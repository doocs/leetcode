using u64=unsigned long long;
int prime[]={2, 3, 5, 7};

class Solution {
public:
    array<int, 4> exp={0};

    bool primeFactor(u64 x) {
        if (x==0) return 0;

        // Count powers of 2
        exp[0]=countr_zero(x);
        x>>=exp[0];

        // Count powers of 3, 5, and 7
        for (int i=1; i< 4; i++) {
            int p=prime[i];
            for (; x%p==0; x/=p) 
                exp[i]++;
        }
        return x==1;
    }

    void modifyExp(char c, int dir) {
        int x=c-'0';
        switch (x) {
            case 2: exp[0]+=dir; break;
            case 4: exp[0]+=dir<<1; break;
            case 8: exp[0]+=dir*3; break;
            case 3: exp[1]+=dir; break;
            case 5: exp[2]+=dir; break;
            case 6: exp[0]+=dir; exp[1]+=dir; break;
            case 7: exp[3]+=dir; break;
            case 9: exp[1]+=dir<<1; break;
        }
    }

    string buildSuffix(int len, bool &valid) {
        
        int digit[10]={0};

        int e0=max(0, exp[0]);
        int e1=max(0, exp[1]);
        int e2=max(0, exp[2]);
        int e3=max(0, exp[3]);

        digit[8]=e0/3;
        int r0=e0%3;

        digit[9]=e1>>1;
        int r1=e1&1;

        digit[5]=e2;
        digit[7]=e3;

        if (r0==1 && r1==1) 
            digit[6]=1;
        else if (r0==2 && r1==1) {
            digit[2]=1;
            digit[6]=1;
        } 
        else {
            if (r0==1) digit[2]=1;
            else if (r0==2) digit[4]=1;
            if (r1==1) digit[3]=1;
        }

        int total_digits = 0;
        for (int i=2; i<=9; i++) total_digits+=digit[i];

        if (total_digits>len) {
            valid=0;
            return "";
        }

        digit[1]=len-total_digits;
        valid=1;

        string ans;
        for (int i=1; i<=9; i++) {
            ans.append(digit[i], '0'+i);
        }
        return ans;
    }

    string smallestNumber(string& num, long long t) {
        if (!primeFactor(t)) return "-1";

        int n=num.size();
        auto origExp=exp;

        // 1. Check if num itself works
        bool zeroFound=0;
        int firstZero=-1;
        for (int i=0; i<n; i++) {
            if (num[i]=='0') {
                zeroFound=1;
                firstZero=i;
                break;
            }
            modifyExp(num[i], -1);
        }

        bool valid=0;
        if (!zeroFound) {
            buildSuffix(0, valid);
            if (valid) return num;
        }

        //Try prefix matching from right to left
        int limit=zeroFound ? firstZero : n-1;

        exp=origExp;
        for (int i=0; i <limit; i++) 
            modifyExp(num[i], -1);
        

        for (int i=limit; i>= 0; i--) {
            int startDigit=(i<n && num[i]!='0')?(num[i]-'0'+1):1;
            for (int d=startDigit; d<=9; d++) {
                modifyExp('0'+d, -1);
                string suffix=buildSuffix(n-1-i, valid);
                if (valid) {
                    return num.substr(0, i)+(char)('0'+ d)+suffix;
                }
                modifyExp('0'+d, +1);
            }
            if (i>0) 
                modifyExp(num[i-1], +1);
        }

        // Expand length if necessary
        exp=origExp;
        int targetLen=n+1;
        while (1) {
            string suffix=buildSuffix(targetLen, valid);
            if (valid) return suffix;
            targetLen++;
        }
    }
};
