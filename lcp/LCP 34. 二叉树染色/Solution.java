class Solution {
    Map<TreeNode,int[]> map=new HashMap<>();
    public int maxValue(TreeNode root, int k) {
        calclulateMaxScore(root,k);
        int ans=0;
        int a[]=map.get(root);
        for(int i=0;i<=k;i++){ans=Math.max(ans,a[i]);}
        return ans;
    }
    public void calclulateMaxScore(TreeNode t,int k){
        int a[]=new int[12];
        if(t.left==t.right){a[1]=t.val;}
        else if(t.left==null){
            calclulateMaxScore(t.right,k);
            int r[]=map.get(t.right);
            for(int i=1;i<=k;i++){a[i]=r[i-1]+t.val;}
            for(int i=0;i<=k;i++){a[0]=Math.max(a[0],r[i]);}
        }
        else if(t.right==null){
            calclulateMaxScore(t.left,k);
            int l[]=map.get(t.left);
            for(int i=1;i<=k;i++){a[i]=l[i-1]+t.val;}
            for(int i=0;i<=k;i++){a[0]=Math.max(a[0],l[i]);}
        }
        else{
            calclulateMaxScore(t.left,k);
            calclulateMaxScore(t.right,k);
            int l[]=map.get(t.left);
            int r[]=map.get(t.right);
            //左右加起来的节点数为i-1，
            for(int i=1;i<=k;i++){for(int j=0;j<i;j++){a[i]=Math.max(a[i],t.val+l[j]+r[i-1-j]);}}
            int lMax=0,rMax=0;
            for(int i=0;i<=k;i++){
                lMax=Math.max(lMax,l[i]);
                rMax=Math.max(rMax,r[i]);
            }
            a[0]=lMax+rMax;
        }
        map.put(t,a);
    }
}
