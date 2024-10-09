public class Solution {
    public bool ValidateStackSequences(int[] pushed, int[] popped) {
        Stack<int> stk = new Stack<int>();
        int i = 0;
        
        foreach (int x in pushed) {
            stk.Push(x);
            while (stk.Count > 0 && stk.Peek() == popped[i]) {
                stk.Pop();
                i++;
            }
        }
        
        return i == popped.Length;
    }
}