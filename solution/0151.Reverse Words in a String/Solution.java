class Solution {
    public String reverseWords(String s) {
        int length = s.length();
        if(length ==0)return s;
        char[] res=new char[length];
        int len=helper(s.toCharArray(), length -1,res,0,0);
        return new String(res,0,len);
    }
    private int helper(char[] ch,int r,char[] res,int l,int len){
        while(r>=0&&ch[r]==' ') r--;
        if(r<0)return Math.max(0,len-1);
        int rigth=r;
        while(r>=0&&ch[r]!=' ') r--;
        len+=rigth-r+1;
        for(int left=r+1;left<=rigth;left++,l++) res[l] = ch[left];
        if(l<res.length) res[l++] = ' ';
        return helper(ch,r,res,l,len);
    }
}