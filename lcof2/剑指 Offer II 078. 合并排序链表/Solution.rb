# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode[]} lists
# @return {ListNode}
def merge_k_lists(lists)
  n = lists.length
  i = 1
  while i < n
      lists[i] = merge_two_lists(lists[i - 1], lists[i])
      i += 1
  end
  lists[n - 1]
end

def merge_two_lists(l1, l2)
dummy = ListNode.new()
cur = dummy
while l1 && l2
    if l1.val <= l2.val
        cur.next = l1
        l1 = l1.next
    else
        cur.next = l2
        l2 = l2.next
    end
    cur = cur.next
end
cur.next = l1 || l2
dummy.next
end