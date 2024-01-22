class Solution {
    public long minimumCost(int[] nums, int k, int dist) {
        long result = Long.MAX_VALUE, sum = 0l;
        int n = nums.length;
        TreeSet<Integer> set1 = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
        TreeSet<Integer> set2 = new TreeSet<>((a, b) -> nums[a] == nums[b] ? a - b : nums[a] - nums[b]);
        for(int i=1;i<n;i++)
        {
            set1.add(i);
            sum+=nums[i];
            if(set1.size()>=k)
            {
                int x=set1.pollLast();
                sum-=nums[x];
                set2.add(x);
            }
            if(i-dist>0)
            {
                result=Math.min(result,sum);
                int temp=i-dist;
                if(set1.contains(temp))
                {
                    set1.remove(temp);
                    sum-=nums[temp];
                    if(set2.size()>0)
                    {
                        int y=set2.pollFirst();
                        sum+=nums[y];
                        set1.add(y);
                    }
                }
                else{
                    set2.remove(i-dist);
                }
            }
        }
        return result+nums[0];
    }
}
