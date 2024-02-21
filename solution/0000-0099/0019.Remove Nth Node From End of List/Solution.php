# Definition for singly-linked list.
# class ListNode {
#     public $val;
#     public $next;

#     public function __construct($val = 0, $next = null)
#     {
#         $this->val = $val;
#         $this->next = $next;
#     }
# }

class Solution {
    /**
     * @param ListNode $head
     * @param int $n
     * @return ListNode
     */

    function removeNthFromEnd($head, $n) {
        $dummy = new ListNode(0);
        $dummy->next = $head;

        $first = $dummy;
        $second = $dummy;

        for ($i = 0; $i <= $n; $i++) {
            $second = $second->next;
        }

        while ($second != null) {
            $first = $first->next;
            $second = $second->next;
        }

        $first->next = $first->next->next;

        return $dummy->next;
    }
}
