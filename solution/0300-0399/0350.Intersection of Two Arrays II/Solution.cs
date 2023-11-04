public class Solution {
    public int[] Intersect(int[] nums1, int[] nums2) {
        HashSet<int> hs1 = new HashSet<int>(nums1.Concat(nums2).ToArray());
        Dictionary<int,int> dict = new Dictionary<int,int>();
        List<int> result = new List<int>();

        foreach(int value in hs1){
            dict[value] = 0;
        }

        foreach(int value in nums1)
        {
            if(dict.ContainsKey(value))
            {
                dict[value]+=1;
            }
            else
            {
                dict[value] = 1;
            }
        }

        foreach(int value in nums2)
        {
            if(dict[value] > 0)
            {
                result.Add(value);
                dict[value] -=1;
            }
        }

        return result.ToArray();
    }
}