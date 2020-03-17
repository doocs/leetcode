var hasCycle2 = function(head) {
  let slow = head
  let fast = head
  while (fast !== null && fast.next !== null) {
    slow = slow.next
    fast = fast.next.next
    if (slow === fast) {
      return true
    }
  }
  return false
}

var hasCycle3 = function(head) {
  let arr = []
  while (head !== null) {
    if (arr.includes(head)) {
      return true
    }
    arr.push(head)
    head = head.next
  }
  return false
}