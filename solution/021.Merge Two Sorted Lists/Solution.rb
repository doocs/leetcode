# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val)
#         @val = val
#         @next = nil
#     end
# end

# @param {ListNode} l1
# @param {ListNode} l2
# @return {ListNode}
def merge_two_lists(l1, l2)
  return l1 if l2.nil?
  return l2 if l1.nil?

  head = ListNode.new(0)
  temp = head

  until l1.nil? && l2.nil?
    if l1.nil?
      head.next = l2
      break
    elsif l2.nil?
      head.next = l1
      break
    elsif l1.val < l2.val
      head.next = ListNode.new(l1.val)
      head = head.next
      l1 = l1.next
    else
      head.next = ListNode.new(l2.val)
      head = head.next
      l2 = l2.next
    end
  end
  temp.next
end

