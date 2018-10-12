# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val)
#         @val = val
#         @next = nil
#     end
# end

# @param {ListNode} head
# @param {Integer} n
# @return {ListNode}
def remove_nth_from_end(head, n)
  return nil if head.next.nil?
  count = 1
  q = head
  s = head

  while q.next do
    q = q.next
    s = s.next if count > n
    count += 1
  end
  return head.next if count == n
  s.next = s.next.next
  head
end
