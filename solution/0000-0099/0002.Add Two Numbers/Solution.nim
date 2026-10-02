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
proc addTwoNumbers(l1: var SinglyLinkedList, l2: var SinglyLinkedList): SinglyLinkedList[int] =
  var
    aggregate: SinglyLinkedList[int]
    p = l1.head
    q = l2.head
    carry = 0

  while not p.isNil or not q.isNil or carry > 0:
    var sum = carry
    if not p.isNil:
      sum += p.value
      p = p.next
    if not q.isNil:
      sum += q.value
      q = q.next
    aggregate.append(sum mod 10)
    carry = sum div 10

  result = aggregate
