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
     * @return Integer
     */
    function getDecimalValue($head) {
        $rs = array();
        while ($head != null) {
            array_push($rs, $head->val);
            $head = $head->next;
        }
        $rsStr = implode($rs);
        return bindec($rsStr);
    }
}
