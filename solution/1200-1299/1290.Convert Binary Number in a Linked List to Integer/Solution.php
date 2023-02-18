class Solution {
    /**
     * @param ListNode $head
     * @return Integer
     */
    function getDecimalValue($head) {
    $rs = array();
    while ($head != null){
        array_push($rs,$head->val);
        $head = $head->next;
    }
        $rsStr = implode($rs);
        return bindec($rsStr);
    }
}