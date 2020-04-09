class Solution {
    private List<List<String>> res;
    public List<List<String>> partition(String s) {
        res= new ArrayList<>();
        func(new ArrayList<>(),0,s);
        return res;
    }
    private void func(List<String> temp, int start, String str){
        if(start>=str.length()){
            res.add(new ArrayList<>(temp));
            return;
        }
        int ed=str.indexOf(str.charAt(start),start+1);
        while(ed>0){
            int s=start;
            int e=ed;
            boolean flag=false;
            while(s<e){
                if(str.charAt(s)==str.charAt(e)){
                    s++;
                    e--;
                } else{
                    flag=true;
                    break;
                }
            }
            if(!flag){
                temp.add(str.substring(start,ed+1));
                func(temp,ed+1,str);
                temp.remove(temp.size()-1);
            }
            ed=str.indexOf(str.charAt(start),ed+1);
        }
        temp.add(str.substring(start,start+1));
        func(temp,start+1,str);
        temp.remove(temp.size()-1);
    }
}