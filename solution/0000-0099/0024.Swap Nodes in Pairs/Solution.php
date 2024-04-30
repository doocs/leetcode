# Definition for singly-linked list.
# class ListNode {
#    public $val;
#    public $next;
#    public function __construct($val = 0, $next = null)
#    {
#        $this->val = $val;
#        $this->next = $next;
#    }
# }

class Solution {
    /**
     * @param ListNode $head
     * @return ListNode
     */

    function swapPairs($head) {
        $dummy = new ListNode(0);
        $dummy->next = $head;
        $prev = $dummy;

        while ($head !== null && $head->next !== null) {
            $first = $head;
            $second = $head->next;

            $first->next = $second->next;
            $second->next = $first;
            $prev->next = $second;

            $prev = $first;
            $head = $first->next;
        }

        return $dummy->next;
    }
}
