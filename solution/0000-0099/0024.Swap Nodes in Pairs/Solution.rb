# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode} head
# @return {ListNode}
def swap_pairs(head)
  dummy = ListNode.new(0, head)
  pre = dummy
  cur = head
  while !cur.nil? && !cur.next.nil?
      t = cur.next
      cur.next = t.next
      t.next = cur
      pre.next = t
      pre = cur
      cur = cur.next
  end
  dummy.next
end