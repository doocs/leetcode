# Definition for singly-linked list.
class ListNode {
    public $val;
    public $next;
    public function __construct($val = 0, $next = null) {
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
        $pq = [];
        if ($lists != null) {
            foreach ($lists as $head) {
                if ($head != null) {
                    $pq[] = $head;
                }
            }
        }
        $this->heapify($pq);
        $dummy = new ListNode();
        $cur = $dummy;
        while (count($pq) > 0) {
            $node = $this->heappop($pq);
            if ($node->next != null) {
                $this->heappush($pq, $node->next);
            }
            $cur->next = $node;
            $cur = $node;
        }
        return $dummy->next;
    }

    function heapify(&$pq) {
        for ($i = intdiv(count($pq), 2) - 1; $i >= 0; $i--) {
            $this->siftDown($pq, $i);
        }
    }

    function heappush(&$pq, $node) {
        $pq[] = $node;
        $this->siftUp($pq, count($pq) - 1);
    }

    function heappop(&$pq) {
        $top = $pq[0];
        $last = array_pop($pq);
        if (count($pq) > 0) {
            $pq[0] = $last;
            $this->siftDown($pq, 0);
        }
        return $top;
    }

    function siftUp(&$pq, $i) {
        while ($i > 0) {
            $p = intdiv($i - 1, 2);
            if ($pq[$p]->val <= $pq[$i]->val) {
                break;
            }
            [$pq[$p], $pq[$i]] = [$pq[$i], $pq[$p]];
            $i = $p;
        }
    }

    function siftDown(&$pq, $i) {
        $n = count($pq);
        while (true) {
            $s = $i;
            $l = $i * 2 + 1;
            $r = $l + 1;
            if ($l < $n && $pq[$l]->val < $pq[$s]->val) {
                $s = $l;
            }
            if ($r < $n && $pq[$r]->val < $pq[$s]->val) {
                $s = $r;
            }
            if ($s == $i) {
                break;
            }
            [$pq[$s], $pq[$i]] = [$pq[$i], $pq[$s]];
            $i = $s;
        }
    }
}
