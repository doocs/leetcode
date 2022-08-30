public class Solution {
    public bool ValidateStackSequences(int[] pushed, int[] popped) {
        Stack<int> stk = new Stack<int>();
        int j = 0;
        foreach (int x in pushed)
        {
            stk.Push(x);
            while (stk.Count != 0 && stk.Peek() == popped[j]) {
                stk.Pop();
                ++j;
            }
        }
        return stk.Count == 0;
    }
}