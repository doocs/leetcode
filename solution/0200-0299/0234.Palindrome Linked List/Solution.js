var isPalindrome2 = function (head) {
  if (!head || !head.next) {
    return true
  }
  let slow = head
  let fast = head
  while (fast !== null && fast.next !== null) {
    slow = slow.next
    fast = fast.next.next
  }
  if (fast !== null) {
    slow = slow.next
  }

  let p = new ListNode(0)
  while (slow !== null) {
    let t = slow.next
    slow.next = p.next
    p.next = slow
    slow = t
  }

  let left = head
  let right = p.next
  while (right !== null) {
    if (left.val !== right.val) {
      return false
    }
    left = left.next
    right = right.next
  }
  return true
};

var isPalindrom3 = function (head) {
  let arr = [];
  let next = head;
  while (next) {
    arr.push(next.val);
    next = next.next;
  }
  let len = Math.trunc(arr.length / 2);
  for (let i = 0; i < len; i++) {
    if (arr[i] !== arr[arr.length - 1 - i]) {
      return false;
    }
  }
  return true;
}

var isPalindrome = function (head) {
  let arr = []
  while (head) {
    arr.push(head.val)
    head = head.next
  }
  return arr.join('') === arr.reverse().join('')
}