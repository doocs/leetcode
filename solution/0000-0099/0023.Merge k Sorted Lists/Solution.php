# Definition for singly-linked list.
class ListNode {
    public $val;
    public $next;
    public function __construct($val = 0, $next = null)
    {
        $this->val = $val;
        $this->next = $next;
    }
}

class Solution {
    /**
     * @param ListNode[] $lists
     * @return ListNode
     */

    function mergeKLists($lists) {
        $numLists = count($lists);

        if ($numLists === 0) {
            return null;
        }
        while ($numLists > 1) {
            $mid = intval($numLists / 2);
            for ($i = 0; $i < $mid; $i++) {
                $lists[$i] = $this->mergeTwoLists($lists[$i], $lists[$numLists - $i - 1]);
            }
            $numLists = intval(($numLists + 1) / 2);
        }
        return $lists[0];
    }

    function mergeTwoLists($list1, $list2) {
        $dummy = new ListNode(0);
        $current = $dummy;

        while ($list1 != null && $list2 != null) {
            if ($list1->val <= $list2->val) {
                $current->next = $list1;
                $list1 = $list1->next;
            } else {
                $current->next = $list2;
                $list2 = $list2->next;
            }
            $current = $current->next;
        }
        if ($list1 != null) {
            $current->next = $list1;
        } elseif ($list2 != null) {
            $current->next = $list2;
        }
        return $dummy->next;
    }
}
