# Definition for polynomial singly-linked list.
# class PolyNode:
#     def __init__(self, x=0, y=0, next=None):
#         self.coefficient = x
#         self.power = y
#         self.next = next


class Solution:
    def addPoly(self, poly1: 'PolyNode', poly2: 'PolyNode') -> 'PolyNode':
        dummy = PolyNode()
        cur = dummy
        while poly1 or poly2:
            if poly1 is None or (poly2 and poly2.power > poly1.power):
                cur.next = poly2
                cur = cur.next
                poly2 = poly2.next
            elif poly2 is None or (poly1 and poly1.power > poly2.power):
                cur.next = poly1
                cur = cur.next
                poly1 = poly1.next
            else:
                val = poly1.coefficient + poly2.coefficient
                if val != 0:
                    cur.next = PolyNode(x=val, y=poly1.power)
                    cur = cur.next
                poly1 = poly1.next
                poly2 = poly2.next
        cur.next = None
        return dummy.next
