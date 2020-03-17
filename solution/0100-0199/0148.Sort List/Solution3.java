/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 *
 * O(nlogn) ==》 time complexity
 * O(n)     ==》space complexity
 */
class Solution {

    public ListNode sortList(ListNode head) {
        List<Integer> numList = new ArrayList<>();
        while (head != null) {
            numList.add(head.val);
            head = head.next;
        }
        Collections.sort(numList);

        head = new ListNode(0);
        ListNode res = head;
        for (Integer num : numList) {
            head.next = new ListNode(num);
            head = head.next;
        }
        return res.next;
    }
}