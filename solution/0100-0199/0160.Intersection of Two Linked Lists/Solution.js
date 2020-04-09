var getIntersectionNode2 = function(headA, headB) {
  if (!headA || !headB) {
    return null
  }
  let q = headA, p = headB
  let lengthA = 1
  let lengthB = 1
  while(q.next) {
    q = q.next
    lengthA++
  }
  while (p.next) {
    p = p.next
    lengthB++
  }
  if (q !== p) {
    return null 
  }

  q = headA, p = headB
  if (lengthA > lengthB) {
    for (let i = 0; i < lengthA - lengthB; i++) {
      q = q.next
    }
  } else {
    for (let i = 0; i < lengthB - lengthA; i++) {
      p = p.next
    }
  }
  while (q !== p && q !== null) {
    q = q.next
    p = p.next
  }
  return q
};

var getIntersectionNode = function(headA, headB) {
  let p1 = headA;
	let p2 = headB;
	while (p1 != p2) {
		p1 = p1 ? p1.next : headB;
		p2 = p2 ? p2.next : headA;
	}
	return p1;
}