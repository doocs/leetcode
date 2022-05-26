# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val)
#         @val = val
#         @next = nil
#     end
# end

# @param {ListNode} head
# @return {ListNode}
def swap_pairs(head)
  if head.nil? || head.next.nil?
    return head
  end

  res = head.next
  tmp = head.next.next
  head.next.next = head
  head.next = swap_pairs(tmp)

  res
end
