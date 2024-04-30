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
     * @param int $k
     * @return ListNode
     */

    function reverseKGroup($head, $k) {
        $dummy = new ListNode(0);
        $dummy->next = $head;
        $prevGroupTail = $dummy;

        while ($head !== null) {
            $count = 0;
            $groupHead = $head;
            $groupTail = $head;

            while ($count < $k && $head !== null) {
                $head = $head->next;
                $count++;
            }
            if ($count < $k) {
                $prevGroupTail->next = $groupHead;
                break;
            }

            $prev = null;
            for ($i = 0; $i < $k; $i++) {
                $next = $groupHead->next;
                $groupHead->next = $prev;
                $prev = $groupHead;
                $groupHead = $next;
            }
            $prevGroupTail->next = $prev;
            $prevGroupTail = $groupTail;
        }

        return $dummy->next;
    }
}
