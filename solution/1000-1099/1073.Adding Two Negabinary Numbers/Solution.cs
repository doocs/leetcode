public class Solution {
    public int[] AddNegabinary(int[] arr1, int[] arr2) {
        int i = arr1.Length - 1, j = arr2.Length - 1;
        List<int> ans = new List<int>();
        for (int c = 0; i >= 0 || j >= 0 || c != 0; --i, --j) {
            int a = i < 0 ? 0 : arr1[i];
            int b = j < 0 ? 0 : arr2[j];
            int x = a + b + c;
            c = 0;
            if (x >= 2) {
                x -= 2;
                c -= 1;
            } else if (x == -1) {
                x = 1;
                c = 1;
            }
            ans.Add(x);
        }
        while (ans.Count > 1 && ans[ans.Count - 1] == 0) {
            ans.RemoveAt(ans.Count - 1);
        }
        ans.Reverse();
        return ans.ToArray();
    }
}
