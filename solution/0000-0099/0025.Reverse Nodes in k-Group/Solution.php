/**
 * Definition for a singly-linked list.
 * class ListNode {
 *     public $val = 0;
 *     public $next = null;
 *     function __construct($val = 0, $next = null) {
 *         $this->val = $val;
 *         $this->next = $next;
 *     }
 * }
 */
class Solution {
    /**
     * @param ListNode $head
     * @param Integer $k
     * @return ListNode
     */
    function reverseKGroup($head, $k) {
        $dummy = new ListNode(0);
        $dummy->next = $head;
        $pre = $dummy;

        while ($pre !== null) {
            $cur = $pre;
            for ($i = 0; $i < $k; $i++) {
                if ($cur->next === null) {
                    return $dummy->next;
                }
                $cur = $cur->next;
            }

            $node = $pre->next;
            $nxt = $cur->next;
            $cur->next = null;
            $pre->next = $this->reverse($node);
            $node->next = $nxt;
            $pre = $node;
        }

        return $dummy->next;
    }

    /**
     * Helper function to reverse a linked list.
     * @param ListNode $head
     * @return ListNode
     */
    function reverse($head) {
        $prev = null;
        $cur = $head;
        while ($cur !== null) {
            $nxt = $cur->next;
            $cur->next = $prev;
            $prev = $cur;
            $cur = $nxt;
        }
        return $prev;
    }
}
