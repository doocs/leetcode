# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode} head
# @param {Integer} n
# @return {ListNode}
def remove_nth_from_end(head, n)
  dummy = ListNode.new(0, head)
  fast = slow = dummy
  while n > 0
      fast = fast.next
      n -= 1
  end
  while fast.next
      slow = slow.next
      fast = fast.next
  end
  slow.next = slow.next.next
  return dummy.next
end