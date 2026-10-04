#[
    # Driver code in the solution file
    # Definition for singly-linked list.
    type
    Node[int] = ref object
        value: int
        next: Node[int]

    SinglyLinkedList[T] = object
        head, tail: Node[T]
]#

# More efficient code churning ...
proc addTwoNumbers(l1: var SinglyLinkedList[int], l2: var SinglyLinkedList[int]): SinglyLinkedList[int] =
  var
    aggregate: SinglyLinkedList[int]
    carry = 0
  while not l1.head.isNil or not l2.head.isNil or carry != 0:
    var s = carry
    if not l1.head.isNil:
      s += l1.head.value
      l1.head = l1.head.next
    if not l2.head.isNil:
      s += l2.head.value
      l2.head = l2.head.next
    carry = s div 10
    aggregate.append(s mod 10)
  result = aggregate
