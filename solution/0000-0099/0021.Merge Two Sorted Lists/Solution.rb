# Definition for singly-linked list.
# class ListNode
#     attr_accessor :val, :next
#     def initialize(val = 0, _next = nil)
#         @val = val
#         @next = _next
#     end
# end
# @param {ListNode} list1
# @param {ListNode} list2
# @return {ListNode}
def merge_two_lists(list1, list2)
    dummy = ListNode.new()
    cur = dummy
    while list1 && list2
        if list1.val <= list2.val
            cur.next = list1
            list1 = list1.next
        else
            cur.next = list2
            list2 = list2.next
        end
        cur = cur.next
    end
    cur.next = list1 || list2
    dummy.next
end