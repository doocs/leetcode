import std/[strutils, algorithm]

type
  Node[int] = ref object
    value: int
    next: Node[int]

  SinglyLinkedList[T] = object
    head, tail: Node[T]

proc append[T](list: var SinglyLinkedList[T], data: T = nil): void =
  var node = Node[T](value: data)
  if list.head.isNil:
    list.head = node
    list.tail = node
  else:
    list.tail.next = node
    list.tail = node

proc preview[T](list: SinglyLinkedList[T]): string =
  var s: seq[T]
  var n = list.head
  while not n.isNil:
    s.add n.value
    n = n.next
  result = s.join(" -> ")

proc addTwoNumbers(l1: var SinglyLinkedList, l2: var SinglyLinkedList): SinglyLinkedList[int] =
  var
    aggregate: SinglyLinkedList
    psum: seq[char]
    temp_la, temp_lb: seq[int]

  while not l1.head.isNil:
    temp_la.add(l1.head.value)
    l1.head = l1.head.next

  while not l2.head.isNil:
    temp_lb.add(l2.head.value)
    l2.head = l2.head.next

  psum = reversed($(reversed(temp_la).join("").parseInt() + reversed(temp_lb).join("").parseInt()))
  for i in psum: aggregate.append(($i).parseInt())

  result = aggregate

var list1: SinglyLinkedList[int]
var list2: SinglyLinkedList[int]

for i in @[2, 4, 3]: list1.append(i)
for i in @[5, 6, 4]: list2.append(i)

echo(preview(list1))
echo(preview(list2))
echo(preview(addTwoNumbers(list1, list2)))

