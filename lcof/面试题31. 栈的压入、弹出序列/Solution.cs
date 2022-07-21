public class Solution {
    public bool ValidateStackSequences(int[] pushed, int[] popped) {
        Stack<int> ans = new Stack<int>();
        int q = 0;
        foreach (int x in pushed)
        {
            ans.Push(pushed[x]);
            while (ans.Count != 0 && ans.Peek() == popped[q]) {
                ans.Pop();
                q += 1;
            }
        }
        return ans.Count == 0;
    }
}