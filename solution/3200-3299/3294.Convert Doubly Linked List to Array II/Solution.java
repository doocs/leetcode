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
        while (node != null && node.prev != null) {
            node = node.prev;
        }
        var ans = new ArrayList<Integer>();
        for (; node != null; node = node.next) {
            ans.add(node.val);
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
