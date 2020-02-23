class Solution {
    public int countDigitOne(int n) {
        int index = 1;
        int count = 0;
        int high = n,cur = 0,low = 0;
        while(high > 0){
            high /= 10;
            cur = (n / index) % 10;
            low = n - (n / index) * index;
            if(cur == 0) count += high * index;
            if(cur == 1) count += high * index + low + 1;
            if(cur > 1) count += (high+1) * index;
            index *= 10;
        }
        return count;
    }
}