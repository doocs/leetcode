/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
};
*/

class Solution {
    public int[] toArray(Node node) {
        var cur = node;
        while (cur != null && cur.prev != null) {
            cur = cur.prev;
        }
        var ans = new ArrayList<Integer>();
        while (cur != null) {
            ans.add(cur.val);
            cur = cur.next;
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
