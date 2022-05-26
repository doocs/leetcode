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

def add_two_numbers(l1, l2)
  return l2 if l1 == nil
  return l1 if l2 == nil
  cur_val = l1.val + l2.val
  l3 = ListNode.new(cur_val % 10)
  add = cur_val >= 10 ? 1 : 0
  tmp = l3

  l1 = l1.next
  l2 = l2.next
  while !l1.nil? || !l2.nil? || add > 0
    cur_val = add
    cur_val += l1.nil? ? 0 : l1.val
    cur_val += l2.nil? ? 0 : l2.val
    tmp.next = ListNode.new(cur_val % 10)
    tmp = tmp.next
    add = cur_val >= 10 ? 1 : 0

    l1 = l1.nil? ? l1 : l1.next
    l2 = l2.nil? ? l2 : l2.next
  end

  l3
end
