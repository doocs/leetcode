# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val)
#         @val = val
#         @next = nil
#     end
# end

# @param {ListNode[]} lists
# @return {ListNode}
def merge_k_lists(lists)
  if lists.nil?
    nil
  elsif lists.length == 1
    lists[0]
  else
    cur_list = lists[0]
    for i in 1..lists.length - 1
      cur_list = merge_2_lists(cur_list, lists[i])
    end
    cur_list
  end

end

def merge_2_lists(list_a, list_b)
  if list_a.nil?
    list_b
  elsif list_b.nil?
    list_a
  elsif list_a.val < list_b.val
    list_a.next = merge_2_lists(list_a.next, list_b)
    list_a
  else
    list_b.next = merge_2_lists(list_a, list_b.next)
    list_b
  end
end
