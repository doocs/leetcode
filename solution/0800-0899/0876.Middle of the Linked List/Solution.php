class Solution {

    /**
     * @param ListNode $head
     * @return ListNode
     */
    function middleNode($head) {
        $count = 0;
        $tmpHead = $head;
        while ($tmpHead !=null){
        $tmpHead = $tmpHead->next;
        $count++;
        }
        $len = $count - floor($count/2);
        while ($count != $len ){
        $head = $head->next;
        $count--;
        }
        return $head;
        }
}